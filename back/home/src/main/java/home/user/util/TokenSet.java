package home.user.util;

import java.util.HashSet;

import org.springframework.stereotype.Component;

@Component
public class TokenSet {
	private HashSet<String> set;

	public TokenSet() {
		super();
		this.set = new HashSet<String>();
	}
	
	public boolean add(String token) {
		return this.set.add(token);
	}
	
	public boolean remove(String token) {
		return this.set.remove(token);
	}
	
	public boolean contains(String token) {
		return this.set.contains(token);
	}
}
