package server_package.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "CLIENT")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CLIENT_ID")
    private int id;

    @Column(name = "USER_ID")
    private int userId;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "CLIENT_ACCOUNT", joinColumns = @JoinColumn(name = "CLIENT_ID"), inverseJoinColumns = @JoinColumn(name = "ACCOUNT_ID"))
    private List<Account> accounts;

//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
//        if (obj == null) {
//            return false;
//        }
//        if (getClass() != obj.getClass()) {
//            return false;
//        }
//        final City other = (City) obj;
//        if (this.population != other.population) {
//            return false;
//        }
//        if (!Objects.equals(this.name, other.name)) {
//            return false;
//        }
//        return Objects.equals(this.id, other.id);
//    }
//
//    @Override
//    public String toString() {
//
//        var builder = new StringBuilder();
//        builder.append("City{id=").append(id).append(", name=")
//                .append(name).append(", population=")
//                .append(population).append("}");
//
//        return builder.toString();
//    }
}
