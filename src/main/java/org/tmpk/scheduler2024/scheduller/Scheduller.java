package org.tmpk.scheduler2024.scheduller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.tmpk.scheduler2024.repos.*;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Component
public class Scheduller {
    @Autowired
    TariffsRepo tariffsRepo;
    @Autowired
    ClientsRepo clientsRepo;
    @Autowired
    ClientStatusTypeRepo clientStatusTypeRepo;
    @Autowired
    BalanceRepo balanceRepo;
    @Autowired
    ServiceTariffRepo serviceTariffRepo;
    private static final Logger log = LoggerFactory.getLogger(Scheduller.class);
    @Scheduled(cron = "0 0 0 * * ?")
    public void transactionEverydayTariffs() {//проверяет тарифы, с оплатой раз в день и кто может платит
        var tariffs = tariffsRepo.findAllPerMonth(30);
        for (var tariff: tariffs
             ) {

            var serviceTariffList = serviceTariffRepo.findAllServiceTariffByTariffId(tariff.getId());
            for (var serviceTariff : serviceTariffList
            ) {
                serviceTariff.setIspaided(false);
                var client = clientsRepo.findById(serviceTariff.getClientid()).get();
                var balance = client.getBalance();
                if (balance.getBalanceValue() > -balance.getLimitValue()){
                    balance.setBalanceValue(balance.getBalanceValue() - tariff.getPrice());
                    balance.setModified(LocalDateTime.now());
                    serviceTariff.setIspaided(false);
                    serviceTariffRepo.save(serviceTariff);
                    balanceRepo.save(balance);
                    clientsRepo.save(client);
                    log.info(String.format("ClientId = %s, payment of the ServiceTariffId = %d, price = %s", client.getId(), serviceTariff.getId(), tariff.getPrice().toString()));
                }
                else {
                    client.setClientStatusType(clientStatusTypeRepo.findByName("Блокировка"));
                    clientsRepo.save(client);
                    log.info(String.format("ClientId = %s, blocked", client.getId()));
                }

            }
        }
    }
    @Scheduled(cron = "0 0 0 1 * ?")
    public void transactionEveryMonthTariffs() {//проверяет тарифы, с оплатой раз в месяц и кто может платит
        var tariffs = tariffsRepo.findAllPerMonth(1);
        for (var tariff: tariffs
        ) {

            var serviceTariffList = serviceTariffRepo.findAllServiceTariffByTariffId(tariff.getId());
            for (var serviceTariff : serviceTariffList
            ) {
                serviceTariff.setIspaided(false);
                var client = clientsRepo.findById(serviceTariff.getClientid()).get();
                var balance = client.getBalance();
                if (balance.getBalanceValue() > -balance.getLimitValue()){
                    balance.setBalanceValue(balance.getBalanceValue() - tariff.getPrice());
                    balance.setModified(LocalDateTime.now());
                    serviceTariff.setIspaided(true);
                    serviceTariffRepo.save(serviceTariff);
                    tariffsRepo.save(tariff);
                    balanceRepo.save(balance);
                    clientsRepo.save(client);
                    log.info(String.format("ClientId = %s, payment of the ServiceTariffId = %d, price = %s", client.getId(), serviceTariff.getId(), tariff.getPrice().toString()));
                }
                else {
                    client.setClientStatusType(clientStatusTypeRepo.findByName("Блокировка"));
                    clientsRepo.save(client);
                    log.info(String.format("ClientId = %s, blocked", client.getId()));
                }
            }
        }
    }

    @Scheduled(cron = "0 * * ? * *")//разблокирует клиента если все оплатит и баланс > -лимит
    public void unblocking() {
        log.info("start unblocking");
        var clientsList = clientsRepo.findAll();
        var blockedClientsWithMoney = clientsList
                .stream()
                .filter(client -> client.getClientStatusType().getName().equals("Блокировка")
                        && client.getBalance().getBalanceValue() > 0)
                .toList();
        try {
            for (var client: blockedClientsWithMoney
                 ) {
                var id = client.getId();
                var unpaid = serviceTariffRepo.findAllServiceTariffByClientId(id)
                        .stream()
                        .filter(serviceTariff -> !serviceTariff.getIspaided())
                        .toList();
                for (var forPaid: unpaid
                     ) {
                    if(client.getBalance().getBalanceValue() > -client.getBalance().getLimitValue()){
                        var balance = client.getBalance();
                        balance.setModified(LocalDateTime.now());
                        balance.setBalanceValue(balance.getBalanceValue() - forPaid.getTariffs().getPrice());
                        log.info(String.format("pay serviceTariffId = %d was paid for",forPaid.getId()));
                        balanceRepo.save(balance);
                        forPaid.setIspaided(true);
                        serviceTariffRepo.save(forPaid);
                        if(balance.getBalanceValue() > -balance.getLimitValue()){
                            log.info(String.format("activate clientID = %s",client.getId()));
                            client.setClientStatusType(clientStatusTypeRepo.findByName("Активен"));
                        }
                        clientsRepo.save(client);
                    }
                }

            }
        }catch (Exception e){

        }

    }
}
