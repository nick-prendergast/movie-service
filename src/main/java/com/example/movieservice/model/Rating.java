package com.example.movieservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

@Data
@Entity
@NoArgsConstructor
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotEmpty
    private String title;

    private int rating;

    private BigDecimal boxOffice;

    public void setBoxOffice(String boxOffice) {
        BigDecimal moneyAmount = BigDecimal.ZERO;

        if (!boxOffice.equals("N/A")) {
            Locale locale = new Locale("en", "US");
            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
            try {
                Number money = currencyFormat.parse(boxOffice);
                moneyAmount = BigDecimal.valueOf(money.doubleValue());
            } catch (ParseException pe) {
                pe.printStackTrace();
            }
        }
        this.boxOffice = moneyAmount;
    }
}
