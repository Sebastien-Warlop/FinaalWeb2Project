package domain.model;

import domain.DomainException;

public class Boek {
    private String titel;
    private String auteur;
    private String genre;
    private int rating, aantalPaginas;

    public Boek(String titel, String auteur, String genre, int rating, int aantalPaginas) {
        this.setTitel(titel);
        this.setAuteur(auteur);
        this.setGenre(genre);
        this.setRating(rating);
        this.setAantalPaginas(aantalPaginas);
    }

    public Boek(String titel) {
        this.setTitel(titel);
    }

    public Boek() {
    }

    public String getTitel() {
        return titel;
    }
    public String getAuteur() {
        return auteur;
    }
    public String getGenre() {
        return genre;
    }
    public int getRating() {
        return rating;
    }
    public int getAantalPaginas() {
        return aantalPaginas;
    }

    public void setAantalPaginas(int aantalPaginas) {
        if (aantalPaginas < 0)
            throw new IllegalArgumentException("Vul een nummer in voor het aantal pagina's.");

        this.aantalPaginas = aantalPaginas;
    }

    public void setAuteur(String auteur) {
        if (auteur == null || auteur.trim().isEmpty()) {
            throw new IllegalArgumentException("Vul een auteur in.");
        }
        this.auteur = auteur;
    }

    public void setRating(int rating) {
        if (rating < 0 || rating > 10)
            throw new IllegalArgumentException("Vul een nummer in voor de rating.");

        this.rating = rating;
    }

    public void setTitel(String titel) {
        if (titel == null || titel.isEmpty())
            throw new IllegalArgumentException("Vul een titel in.");

        this.titel = titel;
    }

    public void setGenre(String genre){
        if (genre == null || genre.isEmpty()){
            throw new IllegalArgumentException("Vul een genre in.");
        }
        this.genre = genre;
    }

    public String geefInfo() {
        return getTitel() + ", " + getAuteur() + ", " + getGenre() + ", " + getAantalPaginas() + " pagina's, " + "rating: " + getRating() + "/10";
    }
}
