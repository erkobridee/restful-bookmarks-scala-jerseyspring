package com.erkobridee.restful.bookmarks.scala.jerseyspring.tests.junit

import org.junit.runner.RunWith
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import com.erkobridee.restful.bookmarks.scala.jerseyspring.rest.BookmarkRest
import org.springframework.beans.factory.annotation.Autowired
import org.junit.Test
import com.erkobridee.restful.bookmarks.scala.jerseyspring.tests.Singleton._
import com.erkobridee.restful.bookmarks.scala.jerseyspring.persistence.entity.Bookmark
import junit.framework.Assert
import com.erkobridee.restful.bookmarks.scala.jerseyspring.rest.resource.ResultMessage
import com.erkobridee.restful.bookmarks.scala.jerseyspring.persistence.entity.BookmarkResultData
import javax.ws.rs.core.Response

@RunWith(classOf[SpringJUnit4ClassRunner])
@ContextConfiguration(locations = Array("classpath:META-INF/spring/applicationContext.xml"))
class BookmarkRestTest {

  //----------------------------------------------------------------------------
  
  @Autowired
  val rest: BookmarkRest = null
  
  //----------------------------------------------------------------------------
  // RESTful POST
  
  @Test
  def test_01_Create(): Unit = {
    vo = new Bookmark
    vo.name ="IT RESTFul"
    vo.description = "Insert : Integration Test RESTful"
    vo.url = "http://it.bookmarks.domain/"
	
    vo = rest.create( vo ).getEntity.asInstanceOf[Bookmark]
	
    Assert.assertNotNull( vo )
  }
  //----------------------------------------------------------------------------
  // RESTful GET
  
  @Test
  def test_02_List(): Unit = {
    val response: Response = rest.list(1, 10)
    val r: BookmarkResultData = response.getEntity.asInstanceOf[BookmarkResultData]
    
    Assert.assertTrue( r.getData.size > 0 )
  }
  
  //----------------------------------------------------------------------------  
  // RESTful GET .../{id}
  
  def getById( id: Long ): Response = 
    rest.get( id )
  
  @Test
  def test_03_GetById(): Unit = {
    val r: Response = this.getById( vo.id )
    
    Assert.assertTrue( r.getEntity.isInstanceOf[Bookmark] )    
  }
  
  @Test
  def test_03_GetByInvalidId(): Unit = {
    val r: Response = this.getById( -1 )
    
	Assert.assertTrue( r.getEntity.isInstanceOf[ResultMessage] ) 
  }
  
  //----------------------------------------------------------------------------  
  // RESTful GET .../search/{name}
  
  def getByName( name: String ): BookmarkResultData = 
    rest.search(name, 1, 10).getEntity.asInstanceOf[BookmarkResultData]
  
  @Test
  def test_04_GetByInvalidName(): Unit = {
    val r: BookmarkResultData = getByName( "IT RESTFul Invalid Name" )
    
    Assert.assertFalse( r.getData.size > 0 )
  }
  
  @Test
  def test_04_GetByName(): Unit = {
	val r: BookmarkResultData = getByName( vo.name )
	
    Assert.assertTrue( r.getData.size > 0 )
  }
  
  //----------------------------------------------------------------------------  
  // RESTful PUT .../{id}
  
  @Test
  def test_05_Update(): Unit = {
	val nameUpdated: String = vo.name + "++"

	vo.name = nameUpdated
	vo.description = vo.description + "++"
	vo.url = vo.url + System.currentTimeMillis

	vo = rest.update( vo ).getEntity.asInstanceOf[Bookmark]

	Assert.assertEquals( nameUpdated, vo.name )
  }
  
  //----------------------------------------------------------------------------  
  // RESTful DELETE
  
  def deleteById( id: Long ): ResultMessage =
    rest.remove( id ).getEntity.asInstanceOf[ResultMessage]
  
  @Test
  def test_06_DeleteByInvalidId(): Unit = {
    val message: ResultMessage = this.deleteById( -1 )
		
	Assert.assertEquals( 404, message.code )
  }
  
  @Test
  def test_06_DeleteById(): Unit = {
    val message: ResultMessage = this.deleteById( vo.id )
		
	Assert.assertEquals( 202, message.code )
  }

  //----------------------------------------------------------------------------
  
}