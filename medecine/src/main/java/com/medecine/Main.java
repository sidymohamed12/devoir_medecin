package com.medecine;

import java.util.Scanner;

import com.medecine.core.Repository;
import com.medecine.entities.Medecin;
import com.medecine.entities.Rv;
import com.medecine.repository.MedecinRepository;
import com.medecine.repository.RvRepository;
import com.medecine.service.MedecinService;
import com.medecine.service.RvService;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Repository<Medecin> medecinRepository = new MedecinRepository();
        Repository<Rv> rvRepository = new RvRepository(medecinRepository);
        MedecinService medecinService = new MedecinService(medecinRepository);
        RvService rvService = new RvService(rvRepository);

        int choice;

        do {

            System.out.println("1- Ajouter un medecin");
            System.out.println("2- Lister les medecin");
            System.out.println("3- eneregistrer un RV");
            System.out.println("4- Lister les RV");
            System.out.println("5- Filtrer les RV par medecin");
            System.out.println("6- Lister les RV par date");
            choice = scanner.nextInt();

            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    Medecin medecin = new Medecin();
                    System.out.println("entrer le nom du medecin");
                    medecin.setNom(scanner.nextLine());
                    System.out.println("entrer le prenom du medecin");
                    medecin.setPrenom(scanner.nextLine());
                    medecinService.create(medecin);
                    System.out.println("Medecin créé avec success : " + medecin);

                }
                case 2 -> {
                    medecinService.findAll().forEach(System.out::println);
                }
                case 3 -> {
                    Rv rv = new Rv();
                    System.out.println("entrer la date du rv");
                    rv.setDate(scanner.nextLine());
                    System.out.println("entrer l'heure du rv");
                    rv.setHeure(scanner.nextLine());
                    medecinService.findAll().forEach(System.out::println);
                    System.out.println("choisissez le medecin (veuillez saisir son id) : ");
                    Medecin medecin = medecinService.getById(scanner.nextInt());
                    System.out.println(medecin);
                    rv.setMedecin(medecin);
                    rvService.create(rv);
                }
                case 4 -> {
                    rvService.findAll().forEach(System.out::println);
                }
                case 5 -> {
                    medecinService.findAll().forEach(System.out::println);
                    System.out.println("choisissez le medecin (veuillez saisir son id) : ");
                    int id = scanner.nextInt();
                    rvService.findAll()
                            .stream()
                            .filter(rv -> rv.getMedecin().getId() == id)
                            .forEach(System.out::println);
                }
            }
        } while (choice != 16);
    }

}