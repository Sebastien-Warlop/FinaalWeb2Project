package ui;

import domain.DomainException;
import domain.db.BoekDB;
import domain.model.Boek;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

@WebServlet("/BoekInfo")
public class Servlet extends javax.servlet.http.HttpServlet {

    private BoekDB boekDB = new BoekDB();

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String destination;

        String command = "home";
        if (request.getParameter("command") != null) {
            command = request.getParameter("command");
        }

        switch (command) {
            case "home":
                destination = home(request, response);
                break;
            case "overzicht":
                destination = overzicht(request, response);
                break;
            case "voegToe":
                destination = voegToe(request, response);
                break;
            case "delete":
                destination = delete(request, response);
                break;
            case "verwijderBevestiging":
                destination = "verwijderBevestiging.jsp";
                break;
            case "zoek":
                destination = zoek(request, response);
                break;
            case "logboek":
                destination = "logboek.jsp";
                break;
            case "update":
                destination = update(request, response);
                break;
            case "updateBevestig":
                destination = updateBevestig(request, response);
                break;
            case "sorteer":
                destination = sorteer(request, response);
                break;
            default:
                destination = home(request, response);
        }
        request.getRequestDispatcher(destination).forward(request, response);
    }

    private String sorteer(HttpServletRequest request, HttpServletResponse response) {
        String destination = "BoekInfo?command=overzicht";
        String categorie = request.getParameter("categorie");

        if (categorie != null)
            response.addCookie(new Cookie("sorteer", categorie));
        return destination;
    }


    private String zoek(HttpServletRequest request, HttpServletResponse response) {
        String titel = request.getParameter("titel");
        String destination = "";

        if (titel.equals("")){
            request.setAttribute("error", "Vul een titel in.");
            destination = "zoek.jsp"; //belangrijk!!! => NIET BoekInfo?command=zoek
        }else{
            Boek boek = boekDB.vind(titel);
            if (boek == null){
                destination = "nietGevonden.jsp";
            }else {
                destination = "gevonden.jsp";
                request.setAttribute("boekInfo", boek.geefInfo());
            }
        }
        return destination;
    }

    private String delete(HttpServletRequest request, HttpServletResponse response) {
        String titel = request.getParameter("titel");
        boekDB.verwijder(titel);
        return overzicht(request, response);
    }

    private String voegToe(HttpServletRequest request, HttpServletResponse response) {
        //vanaf week 7 validation
        ArrayList<String> errors = new ArrayList<String>();

        Boek boek = new Boek();

        setTitel(boek, request, errors);
        setAuteur(boek, request, errors);
        setGenre(boek, request, errors);
        setRating(boek, request, errors);
        setAantalPaginas(boek, request, errors);

        if (errors.size() == 0) {
            try {
                boekDB.voegToe(boek);
                return overzicht(request, response);
            } catch (IllegalArgumentException exc) {
                errors.add(exc.getMessage());
            }
        }
        request.setAttribute("errors", errors);
        return "voegToe.jsp";
    }

    private void setTitel(Boek boek, HttpServletRequest request, ArrayList<String> errors) {
        String titel = request.getParameter("titel");
        try {
            boek.setTitel(titel);
            request.setAttribute("titelPreviousValue", titel);
        } catch (IllegalArgumentException exc) {
            errors.add(exc.getMessage());
        }
    }

    private void setAuteur(Boek boek, HttpServletRequest request, ArrayList<String> errors) {
        String auteur = request.getParameter("auteur");
        try {
            boek.setAuteur(auteur);
            request.setAttribute("auteurPreviousValue", auteur);
        } catch (IllegalArgumentException exc) {
            errors.add(exc.getMessage());
        }
    }

    private void setGenre(Boek boek, HttpServletRequest request, ArrayList<String> errors) {
        String genre = request.getParameter("genre");
        try {
            boek.setGenre(genre);
            request.setAttribute("genrePreviousValue", genre);
        } catch (IllegalArgumentException exc) {
            errors.add(exc.getMessage());
        }
    }

    private void setRating(Boek boek, HttpServletRequest request, ArrayList<String> errors) {
        String rating = request.getParameter("rating");
        try {
            boek.setRating(Integer.parseInt(rating));
            request.setAttribute("ratingPreviousValue", rating);
        } catch (NumberFormatException exc) {       //examenvraag: wrm 2x catchen en wrm eerst numberformat
            errors.add("Vul een nummer in voor de rating");
        } catch (IllegalArgumentException exc) {
            errors.add(exc.getMessage());
        }
    }

    private void setAantalPaginas(Boek boek, HttpServletRequest request, ArrayList<String> errors) {
        String aantalPaginas = request.getParameter("aantalPaginas");
        try {
            boek.setAantalPaginas(Integer.parseInt(aantalPaginas));
            request.setAttribute("aantalPaginasPreviousValue", aantalPaginas);
        } catch (NumberFormatException exc) {       //examenvraag: wrm 2x catchen en wrm eerst numberformat
            errors.add("Vul een nummer in voor het aantal pagina's");
        } catch (IllegalArgumentException exc) {
            errors.add(exc.getMessage());
        }
    }

    private String overzicht(HttpServletRequest request, HttpServletResponse response) {
        Cookie c = getCookieWithKey(request, "sorteer");

        List<Boek> boeken = boekDB.vindAlle();
        String sorteerKey = "";

        String categorie = request.getParameter("categorie");
        if (categorie != null)
            sorteerKey = categorie;
        else if (c != null)
            sorteerKey = c.getValue();


        switch (sorteerKey) {
            case "Titel":
                for (int j = 0; j < boeken.size(); j++)
                    for (int i = 0; i < boeken.size() - 1; i++)
                        if (boeken.get(i).getTitel().toLowerCase().compareTo(boeken.get(i+1).getTitel().toLowerCase()) > 0) {
                            Boek old = boeken.get(i);
                            boeken.set(i, boeken.get(i+1));
                            boeken.set(i + 1, old);

                        }
                break;
            case "Auteur":
                for (int j = 0; j < boeken.size(); j++)
                    for (int i = 0; i < boeken.size() - 1; i++)
                        if (boeken.get(i).getAuteur().toLowerCase().compareTo(boeken.get(i+1).getAuteur().toLowerCase()) > 0) {
                            Boek old = boeken.get(i);
                            boeken.set(i, boeken.get(i+1));
                            boeken.set(i + 1, old);

                        }
                break;
            case "Genre":
                for (int j = 0; j < boeken.size(); j++)
                    for (int i = 0; i < boeken.size() - 1; i++)
                        if (boeken.get(i).getGenre().compareTo(boeken.get(i+1).getGenre()) > 0) {
                            Boek old = boeken.get(i);
                            boeken.set(i, boeken.get(i+1));
                            boeken.set(i + 1, old);

                        }
                break;
            case "Rating":
                for (int j = 0; j < boeken.size(); j++)
                    for (int i = 0; i < boeken.size() - 1; i++)
                        if (boeken.get(i).getRating() > (boeken.get(i+1).getRating())) {
                            Boek old = boeken.get(i);
                            boeken.set(i, boeken.get(i+1));
                            boeken.set(i + 1, old);

                        }
                break;
            case "AantalPaginas":
                for (int j = 0; j < boeken.size(); j++)
                    for (int i = 0; i < boeken.size() - 1; i++)
                        if (boeken.get(i).getAantalPaginas() > (boeken.get(i+1).getAantalPaginas())) {
                            Boek old = boeken.get(i);
                            boeken.set(i, boeken.get(i+1));
                            boeken.set(i + 1, old);

                        }
                break;
//            default:
//                for (int j = 0; j < boeken.size(); j++)
//                    for (int i = 0; i < boeken.size() - 1; i++)
//                        if (boeken.get(i).getTitel().compareTo(boeken.get(i+1).getTitel()) > 0) {
//                            Boek old = boeken.get(i);
//                            boeken.set(i, boeken.get(i+1));
//                            boeken.set(i + 1, old);
//
//                        }
//                break;
        }

        Boek langsteBoek = boekDB.langsteBoek();
        request.setAttribute("langsteBoek", langsteBoek);

        request.setAttribute("alleBoeken", boeken);
        return "overzicht.jsp";
    }


    private String home(HttpServletRequest request, HttpServletResponse response) {
        return "index.jsp";
    }


    private String update(HttpServletRequest request, HttpServletResponse response) {
        return "update.jsp";
    }


    private String updateBevestig(HttpServletRequest request, HttpServletResponse response) {
        if (request.getParameter("submit").equals("UPDATE")) {

            ArrayList<String> errors = new ArrayList<>();
            Boek boek = new Boek();
            setTitel(boek, request, errors);
            setAuteur(boek, request, errors);
            setGenre(boek, request, errors);
            setRating(boek, request, errors);
            setAantalPaginas(boek, request, errors);

            if (errors.size() == 0) {
                try {
                    boekDB.updateBoek(boek.getTitel(), boek.getAuteur(), boek.getGenre(), boek.getRating(), boek.getAantalPaginas());


                    //Session
                    HttpSession session = request.getSession();
                    ArrayList<Boek> versiegeschiedenis = (ArrayList<Boek>) session.getAttribute("versiegeschiedenis");

                    if (versiegeschiedenis == null) {
                        versiegeschiedenis = new ArrayList<>();
                        session.setAttribute("versiegeschiedenis", versiegeschiedenis);
                    } try {
                        versiegeschiedenis.add(boek);
                    } catch (IllegalArgumentException exc) {
                        request.setAttribute("error", "geen geldige titel");
                    }

                    return overzicht(request, response);
                } catch (IllegalArgumentException exc) {
                    errors.add(exc.getMessage());
                }
            }
            request.setAttribute("errors", errors);
            return "update.jsp";
        }
        else {
            return overzicht(request, response);
        }
    }



    private Cookie getCookieWithKey(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null)
            return null;
        for (Cookie cookie : cookies
        ) {
            if (cookie.getName().equals(key))
                return cookie;
        }
        return null;
    }
}

