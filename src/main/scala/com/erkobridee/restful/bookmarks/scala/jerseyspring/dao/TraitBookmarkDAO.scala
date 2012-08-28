package com.erkobridee.restful.bookmarks.scala.jerseyspring.dao

import java.util.List
import com.erkobridee.restful.bookmarks.scala.jerseyspring.entity.Bookmark

trait TraitBookmarkDAO {

  def listAll : List[Bookmark]

  def findById(id: Long) : Bookmark

  def findByName(name : String) : List[Bookmark]
  
  def save(value : Bookmark) : Bookmark
  
  def remove(id : Long) : Boolean
  
}