package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.Transfer;;
import com.techelevator.tenmo.model.User;
import com.techelevator.util.BasicLogger;
import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Scanner;


public class TransferService {
    private String BASE_URL;
    private RestTemplate restTemplate = new RestTemplate();
    private AuthenticatedUser currentUser;

    public TransferService(String url, AuthenticatedUser currentUser) {
        this.currentUser = currentUser;
        BASE_URL = url;

    }

    public void sendBucks() {
        User[] users = null;
        Transfer transfer = new Transfer();

        try{
            Scanner scanner = new Scanner(System.in);
            users = (restTemplate.exchange(BASE_URL + "findall", HttpMethod.GET, makeAuthEntity(),User[].class).getBody());
            System.out.println("Users" + "ID" );

            for (User i : users) {
                if(i.getId() != currentUser.getUser().getId()){
                    System.out.println(i.getId() + " " + i.getUsername());
                }
            }
            System.out.println("Enter ID you are sending to: ");
            transfer.setAccountTo(Integer.parseInt(scanner.nextLine()));
            transfer.setAccountFrom(currentUser.getUser().getId());

            if(transfer.getAccountTo() != 0) {
                System.out.print("Enter amount: ");
                try {
                    transfer.setAmount(new BigDecimal(Double.parseDouble(scanner.nextLine())));
                } catch (NumberFormatException e) {
                    System.out.println("Error");
                }
            }
            String output = restTemplate.exchange(BASE_URL + "transfer" , HttpMethod.POST, makeTransferEntity(transfer), String.class).getBody();
            System.out.println(output);
        } catch (RestClientException e) {
            System.out.println("Bad input");
        }catch (NumberFormatException nfe){
            System.out.println("Please Enter Numeric Value");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
///////////
    public void printTransferHistory(Transfer[] transfers, String name) {
        System.out.println("-------------------------------------------\r\n" +
                "                Transfers\r\n     " +
                "ID          From/To                 Amount\r\n" +
                "-------------------------------------------\r\n");

        for(Transfer transfer : transfers) {
            if (transfer.getUserFrom().equals(name)) {
                System.out.println(transfer.getTransferId() + "                From:"
                        + transfer.getUserFrom() + "            " + transfer.getAmount());
                System.out.println(transfer.getTransferId() + "                To:" + transfer.getUserTo() +
                        "             " + transfer.getAmount());

            } else if (transfer.getUserTo().equals(name)) {
                System.out.println(transfer.getTransferId() + "                To:" + transfer.getUserTo() +
                        "              " + transfer.getAmount() );
                System.out.println(transfer.getTransferId() + "              From:" + transfer.getUserFrom() + "               " + transfer.getAmount());
            }
        }

    }
    public Transfer[] transferList(AuthenticatedUser user) {
        long userId = user.getUser().getId();
        Transfer[] transactions = null;
        try{
            ResponseEntity<Transfer[]> response =
                    restTemplate.exchange(BASE_URL+"transfer/"+userId+"/list", HttpMethod.GET,makeAuthEntity(),Transfer[].class);
            transactions = response.getBody();
        }catch (RestClientResponseException | ResourceAccessException e){
            BasicLogger.log(e.getMessage());
        }
        return transactions;
    }


    public  void getTransferById(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter transfer ID to view details (0 to cancel):");
        Long transferId = Long.parseLong(scanner.nextLine());

        if(transferId == 0){
            return;
        }
        else{
                Transfer temp = restTemplate.exchange(BASE_URL + "transfer/" + transferId, HttpMethod.GET, makeAuthEntity(), Transfer.class).getBody();
                System.out.println("-------------------------------------------" +
                        "Transfer Details\r\n" +
                        "--------------------------------------------\r\n" +
                        " Id: " + temp.getTransferId() + "\r\n" +
                        " From: " + temp.getUserFrom() + "\r\n" +
                        " To: " + temp.getUserTo() + "\r\n" +
                        " Type: " + temp.getTransferTypeDesc() + "\r\n" +
                        " Status: " + temp.getTransferStatusDesc() + "\r\n" +
                        " Amount: $" + temp.getAmount());

        }
        }




    private HttpEntity<Transfer> makeTransferEntity(Transfer transfer) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(currentUser.getToken());
        HttpEntity<Transfer> entity = new HttpEntity<>(transfer, headers);
        return entity;
    }

    private HttpEntity makeAuthEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(currentUser.getToken());
        HttpEntity entity = new HttpEntity<>(headers);
        return entity;
    }

}
