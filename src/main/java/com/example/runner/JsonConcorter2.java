package com.example.runner;
	import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

	public class JsonConcorter2 {

		public static void main(String[] args) throws ParseException, JsonParseException, JsonMappingException, IOException {

			try {
				Class.forName("com.mysql.jdbc.driver");

				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root", "password");
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("Select * from Staff");
				while (rs.next()) {
					System.out.println(rs.getInt(0) + " " + rs.getString(1));
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
	}
