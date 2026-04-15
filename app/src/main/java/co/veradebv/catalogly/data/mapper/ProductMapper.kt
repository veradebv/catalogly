package co.veradebv.catalogly.data.mapper

import co.veradebv.catalogly.data.local.db.ProductDao
import co.veradebv.catalogly.data.local.entity.ProductEntity

fun ProductDao.toEntity(): ProductEntity = ProductEntity(
    id = id,
    title = title,
    price = price,
    description = description,
    category = category,
    image = image,
    ratingRate = rating.rate,
    ratingCount = rating.count
)

fun ProductEntity.toDomain(): Product = Product (
    id = id,
    title = title,
    price = price,
    description = description,
    category = category,
    image = image,
    ratingRate = ratingRate,
    ratingCount = ratingCount
)

fun ProductDao.toDomain(): Product = Product (
    id = id,
    title = title,
    price = price,
    description = description,
    category = category,
    image = image,
    ratingRate = rating.rate,
    ratingCount = rating.count
)



