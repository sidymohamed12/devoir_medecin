package com.medecine.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.medecine.core.DataSourceImpl;
import com.medecine.core.Repository;
import com.medecine.entities.Medecin;
import com.medecine.entities.Rv;

public class RvRepository extends DataSourceImpl implements Repository<Rv> {

    private Repository<Medecin> medecinRepository;

    public RvRepository(Repository<Medecin> medecinRepository2) {
        this.medecinRepository = medecinRepository2;
    }

    @Override
    public void insert(Rv value) {
        try {
            String query = "INSERT INTO rv (date, heure, medecinId) VALUES (?, ?, ?)";
            connexion();
            initPreparedStatement(query);
            ps.setString(1, value.getDate());
            ps.setString(2, value.getHeure());
            ps.setInt(3, 1);
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
    public List<Rv> selectAll() {
        List<Rv> rvs = new ArrayList<>();
        try {
            String sql = "SELECT * FROM rv";
            connexion();
            initPreparedStatement(sql);
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                Rv rv = new Rv();
                rv.setId(res.getInt("id"));
                rv.setDate(res.getString("date"));
                rv.setHeure(res.getString("heure"));
                rv.setMedecin(medecinRepository.getById(res.getInt("medecinId")));
                rvs.add(rv);
            }
        } catch (SQLException e) {
            System.out.println("Erreur requête : " + e.getMessage());
        }
        return rvs;
    }

    @Override
    public Rv getById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

}
