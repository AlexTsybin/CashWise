package com.alextsy.storage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alextsy.storage.database.dao.FinanceDao
import com.alextsy.storage.database.entity.CategoryEntity
import com.alextsy.storage.database.entity.CategoryWithSubcategories
import com.alextsy.storage.database.entity.SubcategoryEntity
import com.alextsy.storage.database.entity.TransactionEntity
import com.alextsy.storage.database.entity.TransactionType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [TransactionEntity::class, CategoryEntity::class, SubcategoryEntity::class],
    version = 1
)
abstract class FinanceDatabase : RoomDatabase() {

    abstract fun financeDao(): FinanceDao

    companion object {
        fun prepopulateDatabase(database: FinanceDatabase) {
            CoroutineScope(Dispatchers.IO).launch {
                val dao = database.financeDao()
                val categories = getPredefinedCategories()
                categories.forEach { categoryWithSubcategories ->
                    val categoryId = dao.insertCategory(categoryWithSubcategories.category).toInt()
                    categoryWithSubcategories.subcategories.forEach { subcategory ->
                        dao.insertSubcategory(
                            subcategory.copy(categoryId = categoryId)
                        )
                    }
                }
            }
        }
    }
}

fun getPredefinedCategories(): List<CategoryWithSubcategories> {
    return listOf(
        CategoryWithSubcategories(
            category = CategoryEntity(
                name = "Cash",
                icon = "💵",
                type = TransactionType.CREDIT
            ),
            subcategories = listOf(
                SubcategoryEntity(categoryId = 1, name = "Rent", icon = "🏠"),
                SubcategoryEntity(categoryId = 1, name = "Salary", icon = "💰"),
                SubcategoryEntity(categoryId = 1, name = "Gift", icon = "🎁"),
                SubcategoryEntity(categoryId = 1, name = "Freelance", icon = "💼"),
            )
        ),
        CategoryWithSubcategories(
            category = CategoryEntity(
                name = "Bank",
                icon = "🏦",
                type = TransactionType.CREDIT
            ),
            subcategories = listOf(
                SubcategoryEntity(categoryId = 2, name = "Salary", icon = "💰"),
                SubcategoryEntity(categoryId = 2, name = "Rent", icon = "🏠"),
                SubcategoryEntity(categoryId = 2, name = "Dividend", icon = "📈"),
                SubcategoryEntity(categoryId = 2, name = "Tax Refund", icon = "🧾"),
            )
        ),
        CategoryWithSubcategories(
            category = CategoryEntity(
                name = "Business",
                icon = "🏢",
                type = TransactionType.CREDIT
            ),
            subcategories = listOf(
                SubcategoryEntity(categoryId = 3, name = "Online", icon = "🌐"),
                SubcategoryEntity(categoryId = 3, name = "Revenue", icon = "💸"),
            )
        ),
        CategoryWithSubcategories(
            category = CategoryEntity(
                name = "Other",
                icon = "❓",
                type = TransactionType.CREDIT
            ),
            subcategories = listOf(
                SubcategoryEntity(categoryId = 4, name = "Gambling", icon = "🎲"),
                SubcategoryEntity(categoryId = 4, name = "Insurance", icon = "🛡"),
                SubcategoryEntity(categoryId = 4, name = "Miscellaneous", icon = "🔄"),
            )
        ),
        CategoryWithSubcategories(
            category = CategoryEntity(
                name = "Transport",
                icon = "🚙",
                type = TransactionType.DEBIT
            ),
            subcategories = listOf(
                SubcategoryEntity(categoryId = 5, name = "Car", icon = "🚗"),
                SubcategoryEntity(categoryId = 5, name = "Bus", icon = "🚌"),
                SubcategoryEntity(categoryId = 5, name = "Plane", icon = "✈️"),
                SubcategoryEntity(categoryId = 5, name = "Taxi", icon = "🚕"),
                SubcategoryEntity(categoryId = 5, name = "Train", icon = "🚆"),
            )
        ),
        CategoryWithSubcategories(
            category = CategoryEntity(
                name = "Food & Drinks",
                icon = "🍔",
                type = TransactionType.DEBIT
            ),
            subcategories = listOf(
                SubcategoryEntity(categoryId = 6, name = "Takeaway", icon = "🥡"),
                SubcategoryEntity(categoryId = 6, name = "Dine in", icon = "🍽"),
            )
        ),
        CategoryWithSubcategories(
            category = CategoryEntity(
                name = "Shopping",
                icon = "🛍",
                type = TransactionType.DEBIT
            ),
            subcategories = listOf(
                SubcategoryEntity(categoryId = 7, name = "Clothes", icon = "👕"),
                SubcategoryEntity(categoryId = 7, name = "Electronics", icon = "📱"),
                SubcategoryEntity(categoryId = 7, name = "Groceries", icon = "🛒"),
                SubcategoryEntity(categoryId = 7, name = "Luxury", icon = "💎"),
            )
        ),
        CategoryWithSubcategories(
            category = CategoryEntity(
                name = "Bill payments",
                icon = "💳",
                type = TransactionType.DEBIT
            ),
            subcategories = listOf(
                SubcategoryEntity(categoryId = 8, name = "Electricity", icon = "💡"),
                SubcategoryEntity(categoryId = 8, name = "Water", icon = "🚰"),
                SubcategoryEntity(categoryId = 8, name = "Internet", icon = "🌐"),
                SubcategoryEntity(categoryId = 8, name = "Rent", icon = "🏠"),
                SubcategoryEntity(categoryId = 8, name = "Phone", icon = "☎️"),
            )
        ),
    )
}