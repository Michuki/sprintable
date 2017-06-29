package util.interfaces;

public interface BaseService<T,D> {
	D createEntity(D entity) ;
	public void deleteEntity(T id) ;
	public D updateEntity(T id, D entity) ;
	public D getSingleEntity(T id) ;
	public D getAllEntities() ;
}
