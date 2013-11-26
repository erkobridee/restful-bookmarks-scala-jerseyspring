package com.erkobridee.restful.bookmarks.scala.jerseyspring.tests.junit

import com.erkobridee.restful.bookmarks.scala.jerseyspring.service.BookmarkService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import com.erkobridee.restful.bookmarks.scala.jerseyspring.tests.Singleton._
import com.erkobridee.restful.bookmarks.scala.jerseyspring.persistence.entity.Bookmark
import junit.framework.Assert
import org.junit.Test
import java.util.List
import com.erkobridee.restful.bookmarks.scala.jerseyspring.persistence.entity.BookmarkResultData
import com.erkobridee.restful.bookmarks.scala.jerseyspring.persistence.entity.BookmarkResultData

@RunWith(classOf[SpringJUnit4ClassRunner])
@ContextConfiguration(locations = Array("classpath:META-INF/spring/applicationContext.xml"))
class BookmarkServiceTest {

  // ---------------------------------------------------------------------------

  @Autowired
  val service: BookmarkService = null

  // ---------------------------------------------------------------------------   

  // RESTful POST
  @Test
  def testInsert(): Unit = {
    vo = new Bookmark
    vo.name = "BookmarkServiceTest Name"
    vo.description = "BookmarkServiceTest Description"
    vo.url = "http://service.bookmarkdomain.test/" + System.currentTimeMillis + "/"
    vo = service.insert( vo )

    Assert.assertNotNull( vo.id )
  }

  // RESTful GET .../{id}
  @Test
  def testGetById(): Unit = {
    Assert.assertNotNull( service.getById( vo.id ) )
  }

  // RESTful GET .../search/{name}
  @Test
  def testGetByName(): Unit = {
    val r: BookmarkResultData = service.getByName( vo.name )

    Assert.assertTrue( r.getData.size > 0 )
  }

  // RESTful PUT .../{id}
  @Test
  def testUpdate(): Unit = {
    val nameUpdated: String = vo.name + "++"

    vo.name = nameUpdated
    vo.description = vo.description + "++"
    vo.url = vo.url + System.currentTimeMillis

    vo = service.update( vo )

    Assert.assertEquals( nameUpdated, vo.name )
  }

  // RESTful GET
  @Test
  def testGetAll(): Unit = {
    val r: BookmarkResultData = service.getAll

    Assert.assertTrue( r.getData.size > 0 )
  }

  // RESTful DELETE
  @Test
  def testDelete(): Unit = {
    val id: Long = vo.id

    service.remove( id )

    vo = service.getById( id )

    Assert.assertNull( vo )
  }

}