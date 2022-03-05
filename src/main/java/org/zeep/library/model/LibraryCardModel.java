package org.zeep.library.model;


import lombok.*;
import org.zeep.library.enums.AccountType;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder
@Entity @Table(name = "library_card")
public class LibraryCardModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "issued_date")
    private Date issuedDate;

    @Column(name = "account_type")
    private AccountType accountType;

    // @param: value to encode(isbn), format(qrcode), width, height
    @Column(name = "qrcode") @Lob
    private byte[] qrcode;

    @Column(name = "photo") @Lob
    private byte[] photo;

//    @OneToOne(mappedBy = "libraryCard")
//    private MemberModel memberModel;

    @Column(name = "expires_on")
    private LocalDate expDate;

    public LocalDate getExpDate() {
//        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(issuedDate);
        int month = calendar.get(Calendar.MONTH), new_month;
        int year = calendar.get(Calendar.YEAR);
        System.out.println(month);
        System.out.println(year+" Years");
        if ((month+6) > 11) {
            new_month = (month + 6) % 12;
            System.out.println(new_month+" inna month");
            if ((new_month + 6) < month) {
                year = calendar.get(Calendar.YEAR);
                year += 1;
            }
        } else {
            new_month = month+6;
        }

        return LocalDate.of(year, new_month+1, 20);
    }
}
