package com.erkobridee.restful.bookmarks.scala.jerseyspring.rest

import org.springframework.stereotype.Component
import org.springframework.context.annotation.Scope
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import com.erkobridee.restful.bookmarks.scala.jerseyspring.persistence.dao.TraitBookmarkDAO
import com.erkobridee.restful.bookmarks.scala.jerseyspring.persistence.entity.BookmarkResultData
import java.net.URI
import javax.ws.rs.Path
import javax.ws.rs.core.UriInfo
import javax.ws.rs.core.Context
import javax.ws.rs.core.UriBuilder
import javax.ws.rs.core.Response
import javax.ws.rs._
import javax.ws.rs.core.MediaType._
import javax.ws.rs.core.Response._
import com.erkobridee.restful.bookmarks.scala.jerseyspring.persistence.entity.Bookmark
import com.erkobridee.restful.bookmarks.scala.jerseyspring.rest.resource.ResultMessage


@Component
@Scope("prototype")
@Path("/bookmarks")
class BookmarkRest {

  //----------------------------------------------------------------------------
  
  val log: Logger = LoggerFactory.getLogger(classOf[BookmarkRest])
  
  //----------------------------------------------------------------------------
  
  @Context
  val uriInfo: UriInfo = null
  
  @Autowired
  val dao: TraitBookmarkDAO = null
  
  //----------------------------------------------------------------------------
  
  def getLocation(): URI = 
    this.getLocation( "" )
  
  def getLocation( id: Long ): URI = 
    this.getLocation( "" + id )
  
  def getLocation( add: String ): URI = {
    var uri: URI = null
    
    if( uriInfo != null ) {
      var ub: UriBuilder = uriInfo.getAbsolutePathBuilder
      uri = ub.path( add ).build()
    }
    
    // return
    uri
  }
  
  //----------------------------------------------------------------------------
  
  @GET
  @Path( "search/{find}" )
  @Produces( Array( APPLICATION_JSON ) )
  def search(
      
	@PathParam("find") find: String,
	@DefaultValue("1") @QueryParam("page") page: Int,
	@DefaultValue("10") @QueryParam("size") size: Int
      
  ): Response = {
    
    log.debug("search: " + find + " | page: " + page + " | size: " + size)
    
    val r: BookmarkResultData = dao.findByName(find, page, size)
    
    Response
		.status( Status.OK )
		.entity( r )
		.header( "Allow", "GET" )
		.location( getLocation )
		.build
  }
  
  @GET
  @Produces( Array( APPLICATION_JSON ) )
  def getList(
  
    @DefaultValue("1") @QueryParam("page") page: Int,
	@DefaultValue("10") @QueryParam("size") size: Int
      
  ): Response = {
    
    log.debug("getList | page: " + page + " | size: " + size)
    
    Response
		.status( Status.OK )
		.entity( dao.list( page, size ) )
		.header( "Allow", "GET, POST" )
		.location( getLocation )
		.build
    
    null
  }
  
  
  @GET
  @Path( "{id}" )
  @Produces( Array( APPLICATION_JSON ) )
  def get(
  
    @PathParam("id") id: Long
  
  ): Response = {
    
    val bookmark: Bookmark = dao.findById( id )
    
    var response: Response = null
    
    if(bookmark != null) {
      
      response = Response
					.status( Status.OK )
					.entity( bookmark )
					.header( "Allow", "PUT, DELETE" )
					.location( getLocation )
					.build
      
    } else {
      
      val resultMessage:ResultMessage = 
        new ResultMessage(
        	Status.NOT_FOUND.getStatusCode, 
	        "id: " + id + " not found."
        ) 
      
      response = Response
					.status( Status.NOT_FOUND )
					.entity( resultMessage )
					.build
      
    }
    
    // return
    response
  }
  
  
  @POST
  @Consumes( Array( APPLICATION_JSON ) )
  @Produces( Array( APPLICATION_JSON ) )
  def create( value: Bookmark ): Response = {
    
    log.debug( "create" )
    
    val bookmark: Bookmark = dao.save( value )
    
    // return
    Response
		.status( Status.CREATED )
		.entity( bookmark )
		.header( "Allow", "GET, PUT, DELETE" )
		.location( getLocation( bookmark.id ) )
		.build
		
  }
  
  
  @PUT
  @Consumes( Array( APPLICATION_JSON ) )
  @Produces( Array( APPLICATION_JSON ) )
  def update( value: Bookmark ): Response = {
    
    log.debug( "update" )
    
    val bookmark: Bookmark = dao.save( value )
    
    // return
    Response
		.status( Status.ACCEPTED )
		.entity( bookmark )
		.header( "Allow", "GET, PUT, DELETE" )
		.location( getLocation )
		.build
		
  }
  
  @DELETE
  @Path("{id}")
  @Produces( Array( APPLICATION_JSON ) )
  def remove(
      
    @PathParam("id") id: Long
      
  ): Response = {
    
    val flag: Boolean = dao.remove(id)
    
    log.debug("remove: " + id + " | status: " + flag)
    
    var message: ResultMessage = null
    var response: Response = null
    
    if( flag ) {
      
    	message = new ResultMessage(
    	    Status.ACCEPTED.getStatusCode, 
    	    "id: " + id + " removed."
    	)
			
		response = Response
					.status( Status.ACCEPTED )
					.entity( message )
					.build
      
    } else {

    	message = new ResultMessage(
    	    Status.NOT_FOUND.getStatusCode, 
    	    "id: " + id + " not found."
    	)
			
		response = Response
					.status( Status.NOT_FOUND )
					.entity( message )
					.build      
      
    }
    
    // return
    response
  }
  
}