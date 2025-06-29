package com.practices.practice.feature.java.lombdaexpression.filtrage;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Tu gères un système de comptes bancaires, et tu veux lister uniquement les comptes actifs (isActive == true).
 */
public class ExemplePredicate {

    // Generic fiulter
    static <T> List<T> filter(List<T> inputAccount, Predicate<T> isActivate) {
        List<T> result = new ArrayList<>();
        for (T item : inputAccount) {
            if (isActivate.test(item)) {
                result.add(item);
            }
        }
        return result;
    }

    public static void main(String[] args) {

        List<Account> accounts = List.of(
                new Account("oussama1", true),
                new Account("oussama2", false),
                new Account("oussama3", true),
                new Account("oussama4", false),
                new Account("oussama5", true));

        List<Account> activatedAccount = filter(accounts, Account::activate);

        System.out.println("result: " + activatedAccount);
        activatedAccount.forEach(account -> System.out.println("activated owner is : " + account.owner()));

    }
}
