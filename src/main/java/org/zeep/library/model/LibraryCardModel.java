package org.zeep.library.model;


import lombok.*;
import org.zeep.library.enums.AccountType;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
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

    @OneToOne(mappedBy = "libraryCard")
    private MemberModel memberModel;

    @Column(name = "expires_on")
    private Date expires_on;
}
