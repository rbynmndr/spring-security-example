package com.example.db.customquery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

import com.example.db.model.MunicipalityModel;

@Service
public class Custom {
	
	@Autowired
	JdbcTemplate template;
	
	//reporting 
	public List<MunicipalityModel> getOnlyName(int id){
		String query = "select municipality_name from municipality_tbl where municipality_id ="+id;
		List<MunicipalityModel> lists = template.query(query, new ResultSetExtractor<List<MunicipalityModel>>() {

			@Override
			public List<MunicipalityModel> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<MunicipalityModel> models = new ArrayList<MunicipalityModel>();
				while(rs.next()) {											//
					MunicipalityModel model = new MunicipalityModel();
					model.setMuniciName(rs.getString("municipality_name"));
					models.add(model);
				}
				return models;
			}
			
		});
		return lists;
	}

}
