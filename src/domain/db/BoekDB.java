package domain.db;

import domain.DomainException;
import domain.model.Boek;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class BoekDB {
    private ArrayList<Boek> boeken = new ArrayList<Boek>();

    public BoekDB() {
        this.voegToe(new Boek("De meeste mensen deugen", "Rutger Bregman", "Informatief", 7, 335));
        this.voegToe(new Boek("abc", "Marc", "Kind", 5, 20));
        this.voegToe(new Boek("efg", "Tom", "Avontuur", 8, 22));
        this.voegToe(new Boek("hij", "Bert", "Mysterie", 7, 23));
        this.voegToe(new Boek("klm", "Patrick", "Misdaad", 6, 21));
    }

    public void voegToe(Boek boek) {
        /*if (heeftBoek(boek)) {
            throw new DomainException("Dit boek staat al in de bibliotheek");
        } else {*/
        boeken.add(boek);
        //}
    }

    private boolean heeftBoek(Boek boek) {
        for (Boek b : boeken) {
            if (boek.getTitel().equals(b.getTitel())) {
                return true;
            }
        }
        return false;
    }

    public Boek langsteBoek() {
        if (boeken.get(0) == null) {
            throw new IllegalArgumentException("Er zijn geen boeken toegevoegd");
        }
        Boek aantalPaginas = boeken.get(0);
        for (Boek b : boeken) {
            if (aantalPaginas.getAantalPaginas() < b.getAantalPaginas()) {
                aantalPaginas = b;
            }
        }
        return aantalPaginas;
    }

    public Boek vind(String titel) {
        for (Boek b : boeken) {
            if (b.getTitel().equals(titel)) {
                return b;
            }
        }
        return null;
    }

    public ArrayList<Boek> vindAlle() {
        return boeken;
    }

    public void verwijder(String titel) {
        boeken.remove(this.vind(titel));
    }

    public void updateBoek(String titel, String auteur, String genre, int rating, int aantalPaginas) {
        Boek oudBoek = vind(titel);
        if (titel != null && !titel.equals(oudBoek.getTitel())) {
            oudBoek.setTitel(titel);
        }
        if (auteur != null && !auteur.equals(oudBoek.getAuteur())) {
            oudBoek.setAuteur(auteur);
        }
        if (genre != null && !genre.equals(oudBoek.getGenre())) {
            oudBoek.setGenre(genre);
        }
        if (rating != 0 && rating != oudBoek.getRating()) {
            oudBoek.setRating(rating);
        }
        if (aantalPaginas != 0 && aantalPaginas != oudBoek.getAantalPaginas()) {
            oudBoek.setAantalPaginas(aantalPaginas);
        }
    }

}
