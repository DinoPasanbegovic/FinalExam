

package ca.sheridancollege.pasanbed.beans;

import java.io.Serializable; 

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor 
@Data
public class User implements Serializable{


	private static final long serialVersionUID = 1L;
	
	// SEC_USER database 
	//private int id;
	private Long userId;
	private String userName;
	private String encryptedPassword;
	private String role;
}