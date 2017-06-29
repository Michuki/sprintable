package com.app.model;

import javax.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
public class SocialMedia extends PersistableEntity{

}
