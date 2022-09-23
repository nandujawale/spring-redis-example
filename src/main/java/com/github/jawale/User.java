package com.github.jawale;

import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@RedisHash("User")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

	private String id;
	private Integer lostCounter;
	private Integer stdCounter;
	private Integer recCounter;
	private Integer maxLOSTAllowed;
	private Integer maxSTDAllowed;
	private Integer maxRECtAllowed;
}
