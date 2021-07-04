package com.example.mediamicroservice.dto;


public class CollectionDTO {

	private Long idFavourites;
	 
    private String name;

	public CollectionDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CollectionDTO(Long idFavourites, String name) {
		super();
		this.idFavourites = idFavourites;
		this.name = name;
	}

	public Long getIdFavourites() {
		return idFavourites;
	}

	public void setIdFavourites(Long idFavourites) {
		this.idFavourites = idFavourites;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
    
}
