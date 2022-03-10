package server_package.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ACCOUNT")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Account implements Comparable<Account> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ACCOUNT_ID")
    private int id;

    @Column(name = "BALANCE")
    private int balance;

    @Column(name = "CURRENCY")
    private String currency;

    @Override
    public int compareTo(Account o) {
        return this.id - o.id;
    }

}
