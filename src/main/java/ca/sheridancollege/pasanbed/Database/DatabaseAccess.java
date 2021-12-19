package ca.sheridancollege.pasanbed.Database;

import java.math.BigDecimal;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.pasanbed.beans.Food;
import ca.sheridancollege.pasanbed.beans.User;

@Repository
public class DatabaseAccess {

	
	// we want to use named parameters 
	@Autowired
	protected NamedParameterJdbcTemplate jdbc;
	
	// find user by user name in database 
	public User findUserAccount(String userName) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT * FROM sec_user where userName=:userName";
		parameters.addValue("userName", userName);
		ArrayList<User> users = (ArrayList<User>)jdbc.query(query, parameters,
				new BeanPropertyRowMapper<User>(User.class));
		if (users.size()>0)
			return users.get(0);
		else
			return null;
	}
	
	// returns list of strings from database 
	public List<String> getRolesById(Long long1) {
		ArrayList<String> roles = new ArrayList<String>();
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "select user_role.userId, sec_role.roleName "
				+ "FROM user_role, sec_role "
				+ "WHERE user_role.roleId=sec_role.roleId "
				+ "and userId=:userId";
		parameters.addValue("userId", long1);
		List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);
		for (Map<String, Object> row : rows) {
			roles.add((String)row.get("roleName"));
		}
		return roles;
	}
	
    @Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public void addUser(String userName, String password) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "insert into SEC_User " 
				+ "(userName, encryptedPassword, ENABLED)" 
				+ " values (:userName, :encryptedPassword, 1)";
		parameters.addValue("userName", userName);	
		parameters.addValue("encryptedPassword", 
				passwordEncoder.encode(password));
		jdbc.update(query, parameters);
	}

	public void addRole(Long userId, long roleId) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "insert into user_role (userId, roleId)" 
				+ "values (:userId, :roleId);";
		parameters.addValue("userId", userId);
		parameters.addValue("roleId", roleId);
		jdbc.update(query, parameters);	
	}	
	
	// -------------------------------------------------------------------- //

	public void addFood(Food food) {
		MapSqlParameterSource parameters  = new MapSqlParameterSource();
		String query ="INSERT INTO mem_food (food_name, food_price, food_type) VALUES "+
		"(:name,:price,:type)";
		parameters.addValue("name", food.getName());
		parameters.addValue("price", food.getPrice());
		parameters.addValue("type",food.getType());

		jdbc.update(query, parameters);
}
	
	public ArrayList<Food> getMemFood(){
		String q = "Select * from mem_food";
		ArrayList<Food> foods =  new ArrayList<Food>();
		List<Map<String,Object>> rows=jdbc.queryForList(q, new HashMap<String,Object>());
		
		for(Map<String,Object>row:rows) {
			Food d = new Food();
			d.setId((Integer)row.get("id"));
			d.setName((String)(row.get("food_name")));
			d.setPrice((BigDecimal)(row.get("food_price")));
			d.setType((String)(row.get("food_type")));
			foods.add(d);
		}
		return foods;	
	}
	
	public ArrayList<User> getUsers(){
		String q = "Select * FROM SEC_USER";
		ArrayList<User> urs =  new ArrayList<User>();
		List<Map<String,Object>> rows=jdbc.queryForList(q, new HashMap<String,Object>());
		
		for(Map<String,Object>row:rows) {
			User s = new User();
			s.setUserId((Long)row.get("userId"));
			s.setUserName((String)(row.get("userName")));
			urs.add(s);
		}
		
		return urs;	
	}

	public void deleteUsers(int id) {
		MapSqlParameterSource parameters  = new MapSqlParameterSource();
		String query ="Delete from USER_ROLE WHERE userId =:userId";
		parameters.addValue("userId", id);
		jdbc.update(query, parameters);
		query ="Delete from SEC_USER WHERE UserId =:userId";
		jdbc.update(query, parameters);
	}
	

public void editUsername(User user) {
		
		MapSqlParameterSource parameters  = new MapSqlParameterSource();
		//we are storing items in Map - > Key, Value Pairs
		String query ="Update SEC_USER Set USERNAME=:userName WHERE USERID=:userId";
		parameters.addValue("userId", user.getUserId());
		parameters.addValue("userName", user.getUserName());
		jdbc.update(query, parameters);
	
}
	
	
	public ArrayList<Food> getMemFoodTypeVeg(){
		String q = "SELECT id, food_name, food_price FROM mem_food WHERE FOOD_TYPE = 'veg'";
		ArrayList<Food> vegs =  new ArrayList<Food>();
		List<Map<String,Object>> rows=jdbc.queryForList(q, new HashMap<String,Object>());
		
		for(Map<String,Object>row:rows) {
			Food d = new Food();
			d.setId((Integer)row.get("id"));
			d.setName((String)(row.get("food_name")));
			d.setPrice((BigDecimal)(row.get("food_price")));
			d.setType((String)(row.get("food_type")));
			vegs.add(d);
		}
		
		return vegs;	
	}
	
	public ArrayList<Food> getMemFoodTypeFruit(){
		String q = "SELECT id, food_name, food_price FROM mem_food WHERE FOOD_TYPE = 'fruit'";
		ArrayList<Food> vegs =  new ArrayList<Food>();
		List<Map<String,Object>> rows=jdbc.queryForList(q, new HashMap<String,Object>());
		
		for(Map<String,Object>row:rows) {
			Food d = new Food();
			d.setId((Integer)row.get("id"));
			d.setName((String)(row.get("food_name")));
			d.setPrice((BigDecimal)(row.get("food_price")));
			d.setType((String)(row.get("food_type")));
			vegs.add(d);
		}
		
		return vegs;	
	}
	
	public ArrayList<Food> getMemFoodTypeMeat(){
		String q = "SELECT id, food_name, food_price FROM mem_food WHERE FOOD_TYPE = 'meat'";
		ArrayList<Food> vegs =  new ArrayList<Food>();
		List<Map<String,Object>> rows=jdbc.queryForList(q, new HashMap<String,Object>());
		
		for(Map<String,Object>row:rows) {
			Food d = new Food();
			d.setId((Integer)row.get("id"));
			d.setName((String)(row.get("food_name")));
			d.setPrice((BigDecimal)(row.get("food_price")));
			d.setType((String)(row.get("food_type")));
			vegs.add(d);
		}
		
		return vegs;	
	}
	
	public Food getMemFoodbyId(int id){
		String q = "Select * from mem_food where id =:id";
		ArrayList<Food> foods =  new ArrayList<Food>();
		
		MapSqlParameterSource parameters  = new MapSqlParameterSource();
		parameters.addValue("id", id);
		List<Map<String,Object>> rows=jdbc.queryForList(q, parameters);
		
		for(Map<String,Object>row:rows) {
			Food d = new Food();
			d.setId((Integer)row.get("id"));
			d.setName((String)(row.get("food_name")));
			d.setType((String)(row.get("food_type")));
			d.setPrice((BigDecimal)(row.get("food_price")));
			foods.add(d);
		}
		
		if(foods.size()>0)
			return foods.get(0);
		
		return null;	
	}
	
	public User getUserbyId(int id){
		String q = "Select * from SEC_USER where userId=:id";
		ArrayList<User> urs =  new ArrayList<User>();
		
		MapSqlParameterSource parameters  = new MapSqlParameterSource();
		parameters.addValue("id", id);
		List<Map<String,Object>> rows=jdbc.queryForList(q, parameters);
		
		for(Map<String,Object>row:rows) {
			User d = new User();
			d.setUserId((Long)(row.get("userId")));
			d.setUserName((String)(row.get("userName")));
			d.setEncryptedPassword((String)(row.get("encryptedPassword")));
			urs.add(d);
		}
		
		if(urs.size()>0)
			return urs.get(0);
		
		return null;	
	}
	
	public void editMemFood(Food food) {
		
		MapSqlParameterSource parameters  = new MapSqlParameterSource();
		//we are storing items in Map - > Key, Value Pairs
		String query ="Update mem_food Set food_name=:name, food_price=:price, food_type=:type WHERE id=:id ";
		parameters.addValue("id", food.getId());
		parameters.addValue("name", food.getName());
		parameters.addValue("price", food.getPrice());
		parameters.addValue("type", food.getType());
		
		jdbc.update(query, parameters);
	
}
	
public void editQuantity(Food food) {
		
		MapSqlParameterSource parameters  = new MapSqlParameterSource();
		//we are storing items in Map - > Key, Value Pairs
		String query ="Update mem_food Set food_quantity=:quantity WHERE id=:id ";
		parameters.addValue("id", food.getId());
		parameters.addValue("quantity", food.getQuantity());
	
		jdbc.update(query, parameters);
}

	//admin
	
	public void deleteMemFood(int id) {
		MapSqlParameterSource parameters  = new MapSqlParameterSource();
		String query ="Delete from mem_food WHERE id =:id";
		parameters.addValue("id", id);
		jdbc.update(query, parameters);
	}
	
	public ArrayList<Food> getCheckout(){
		String q = "SELECT * FROM mem_food WHERE food_quantity IS NOT NULL";
		ArrayList<Food> checkouts =  new ArrayList<Food>();
		List<Map<String,Object>> rows=jdbc.queryForList(q, new HashMap<String,Object>());
		
		for(Map<String,Object>row:rows) {
			Food d = new Food();
			d.setId((Integer)row.get("id"));
			d.setName((String)(row.get("food_name")));
			d.setPrice((BigDecimal)(row.get("food_price")));
			d.setQuantity((BigDecimal)(row.get("food_quantity")));
			checkouts.add(d);
		}
		
		return checkouts;	
	}	
}