package com.erkobridee.restful.bookmarks.scala.jerseyspring.persistence.dao

import java.util.List
import com.erkobridee.restful.bookmarks.scala.jerseyspring.persistence.entity.Bookmark
import com.erkobridee.restful.bookmarks.scala.jerseyspring.persistence.entity.BookmarkResultData
import org.hibernate.criterion.Criterion

trait TraitBookmarkDAO {

  def count : Int
  
  def count(criterion: Criterion) : Int
  
  def list : BookmarkResultData
  
  def list(page: Int, size: Int) : BookmarkResultData

  def findById(id: Long) : Bookmark

  def findByName(name : String) : BookmarkResultData
  
  def findByName(name : String, page: Int, size: Int) : BookmarkResultData
  
  def save(value : Bookmark) : Bookmark
  
  def remove(id : Long) : Boolean
  
}