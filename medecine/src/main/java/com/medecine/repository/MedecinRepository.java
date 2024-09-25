package com.medecine.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.medecine.core.DataSourceImpl;
import com.medecine.core.Repository;
import com.medecine.entities.Medecin;

public class MedecinRepository extends DataSourceImpl implements Repository<Medecin> {

    @Override
    public void insert(Medecin value) {
        try {
            String query = "INSERT INTO medecin (nom, prenom) VALUES (?, ?)";
            connexion();
            initPreparedStatement(query);
            ps.setString(1, value.getNom());
            ps.setString(2, value.getPrenom());
            executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                value.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de l'exécution de la requête : " + e.getMessage());
        }
    }

    @Override
    public List<Medecin> selectAll() {
        List<Medecin> medecins = new ArrayList<>();
        try {
            String sql = "SELECT * FROM medecin";
            connexion();
            initPreparedStatement(sql);
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                Medecin medecin = new Medecin();
                medecin.setId(res.getInt("id"));
                medecin.setNom(res.getString("nom"));
                medecin.setPrenom(res.getString("prenom"));
                medecins.add(medecin);
            }
        } catch (SQLException e) {
            System.out.println("Erreur requête : " + e.getMessage());
        }
        return medecins;
    }

    @Override
    public Medecin getById(int id) {
        Medecin medecin = null;
        try {
            String sql = "SELECT * FROM medecin WHERE id = ?";
            connexion();
            initPreparedStatement(sql);
            this.ps.setInt(1, id);
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                medecin = new Medecin();
                medecin.setId(res.getInt("id"));
                medecin.setNom(res.getString("nom"));
                medecin.setPrenom(res.getString("prenom"));
            }
        } catch (SQLException e) {
            System.out.println("Erreur requête : " + e.getMessage());
        }
        return medecin;
    }

}
