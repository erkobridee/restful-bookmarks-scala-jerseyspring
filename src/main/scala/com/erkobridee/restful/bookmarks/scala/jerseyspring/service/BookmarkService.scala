package com.erkobridee.restful.bookmarks.scala.jerseyspring.service

import org.springframework.stereotype.Component
import org.springframework.context.annotation.Scope
import javax.ws.rs.Path
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import com.erkobridee.restful.bookmarks.scala.jerseyspring.persistence.dao.TraitBookmarkDAO
import org.springframework.beans.factory.annotation.Autowired
import javax.ws.rs.core.MediaType._
import java.util.List
import com.erkobridee.restful.bookmarks.scala.jerseyspring.persistence.entity.Bookmark
import javax.ws.rs._
import com.erkobridee.restful.bookmarks.scala.jerseyspring.persistence.entity.BookmarkResultData

@Component
@Scope("prototype")
@Path("/bookmarks")
class BookmarkService {
  // ---------------------------------------------------------------------------

  val log: Logger = LoggerFactory.getLogger(classOf[BookmarkService])

  // ---------------------------------------------------------------------------

  @Autowired
  val dao: TraitBookmarkDAO = null

  // ---------------------------------------------------------------------------

  @GET
  @Produces(Array(APPLICATION_JSON, APPLICATION_XML))
  def getAll(): BookmarkResultData = {
    log.debug("getAll")
    dao.list
  }

  @GET
  @Path("{id}")
  @Produces(Array(APPLICATION_JSON, APPLICATION_XML))
  def getById(@PathParam("id") id: Long): Bookmark = {
    log.debug("getById: " + id)
    dao.findById(id)
  }

  @GET
  @Path("search/{name}")
  @Produces(Array(APPLICATION_JSON, APPLICATION_XML))
  def getByName(@PathParam("name") name: String): BookmarkResultData = {
    log.debug("getByName: " + name)
    dao.findByName(name)
  }

  @POST
  @Consumes(Array(APPLICATION_JSON, APPLICATION_XML))
  @Produces(Array(APPLICATION_JSON, APPLICATION_XML))
  def insert(value: Bookmark): Bookmark = {
    log.debug("insert")
    dao.save(value)
  }

  @PUT
  @Path("{id}")
  @Consumes(Array(APPLICATION_JSON, APPLICATION_XML))
  @Produces(Array(APPLICATION_JSON, APPLICATION_XML))
  def update(value: Bookmark): Bookmark = {
    log.debug("update")
    dao.save(value)
  }

  @DELETE
  @Path("{id}")
  @Produces(Array(APPLICATION_JSON, APPLICATION_XML))
  def remove(@PathParam("id") id: Long): Unit = {
    val flag: Boolean = dao.remove(id)
    log.debug("remove: " + id + " | status: " + flag)
  }

}