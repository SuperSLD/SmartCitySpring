package online.jutter.smartcity.data.db

import online.jutter.smartcity.common.HibernateUtils
import online.jutter.smartcity.common.ext.getAll
import org.hibernate.Session
import org.hibernate.criterion.Criterion
import org.hibernate.criterion.Order
import org.hibernate.criterion.Restrictions
import org.hibernate.criterion.SimpleExpression
import java.io.Serializable
import java.lang.reflect.ParameterizedType


/**
 * Базовый класс репозитория для сущностей.
 * Содержит стандартные методы для взаимодействия с базой.
 *
 * [ENT] сущность hibernate.
 *
 * @author Solyanoy Leonid (solyanoy.leonid@gmail.com)
 */
open class BaseRepository<ENT> {

    var persistentClass: Class<ENT>? = null
    init {
        @Suppress("UNCHECKED_CAST")
        this.persistentClass =
            (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<ENT>?
    }

    /**
     * Вставка в базу сущности [ENT].
     */
    open fun insert(entity: ENT) {
        executeTransaction {
            persist(entity)
        }
    }

    /**
     * получение сущности по ID.
     */
    open fun getById(id: Serializable): ENT? {
        var result: ENT? = null
        executeTransaction {
            result = get(persistentClass!!, id)
        }
        return result
    }

    fun Session.getById(id: Serializable): ENT? {
        return this.get(persistentClass!!, id)
    }

    /**
     * Получение всех сущностей.
     */
    open fun getAll(): MutableList<ENT> {
        val result = mutableListOf<ENT>()
        executeTransaction {
            result.addAll(getAll(persistentClass!!))
        }
        return result
    }

    fun Session.getAll(): MutableList<ENT> {
        return getAll(persistentClass!!)
    }

    /**
     * Обновление сущности.
     */
    open fun update(entity: ENT) {
        executeTransaction {
            update(entity)
        }
    }

    /**
     * Удаление сущности.
     */
    open fun remove(entity: ENT) {
        executeTransaction {
            remove(entity)
        }
    }

    /**
     * Удаление сущности по ID.
     */
    open fun removeById(id: Serializable) {
        executeTransaction {
            remove(get(persistentClass, id))
        }
    }

    fun Session.removeById(id: Serializable) {
        remove(get(persistentClass, id))
    }

    /**
     * Получение страницы из базы,
     * с ограниченным количеством полей
     * и пропуском изначальных N записей.
     *
     * @param count количество записей
     * @param skip количество пропущенных записей
     * @param orderBy сортировать записи по
     * @param restrictions условия которым должны удоволетворять записи
     */
    open fun getPage(
        count: Int,
        skip: Int,
        orderBy: String,
        restrictions: List<SimpleExpression> = mutableListOf()
    ) : MutableList<ENT> {
        val result = mutableListOf<ENT>()
        executeTransaction {
            val criteria = createCriteria(persistentClass)
            criteria.setFirstResult(skip)
            criteria.setMaxResults(count)
            criteria.addOrder(Order.desc(orderBy))
            for (restriction in restrictions) {
                criteria.add(restriction)
            }
            result.addAll(criteria.list().toMutableList() as MutableList<ENT>)
        }
        return result
    }

    /**
     * Получение страницы из базы,
     * с ограниченным количеством полей
     * и пропуском изначальных N записей.
     *
     * @param count количество записей
     * @param skip количество пропущенных записей
     * @param orderBy сортировать записи по
     * @param restrictions условия которым должны удоволетворять записи
     */
    fun Session.getPage(
        count: Int,
        skip: Int,
        orderBy: String,
        restrictions: List<SimpleExpression> = mutableListOf()
    ) : MutableList<ENT> {
        val criteria = createCriteria(persistentClass)
        criteria.setFirstResult(skip)
        criteria.setMaxResults(count)
        criteria.addOrder(Order.desc(orderBy))
        for (restriction in restrictions) {
            criteria.add(restriction)
        }
        return criteria.list().toMutableList() as MutableList<ENT>
    }

    /**
     * Выполнение транзакции.
     * Метод упрощает запуск транзакций и
     * избавляет от необходимости обращаться напрямую
     * к [HibernateUtils] для создания сессии и
     * начала транзакции.
     *
     * @param transaction тело транзакции.
     */
    fun <T> executeTransaction(transaction: Session.() -> T) : T? {
        val session = HibernateUtils.getSessionFactory().openSession()
        val result: T?
        try {
            session.transaction.begin()
            result = transaction(session)
            session.transaction.commit()
        } catch (ex: Exception) {
            throw ex
        } finally {
            session.close()
        }
        return result
    }

    /**
     * Выполнение транзакции.
     * Метод упрощает запуск транзакций и
     * избавляет от необходимости обращаться напрямую
     * к [HibernateUtils] для создания сессии
     * начала транзакции.
     * С калбеком для обработки ошибок.
     *
     * @param transaction транзакция.
     * @param catch калбэк ошибки при выполнении транзакции.
     */
    fun <T> executeTransaction(transaction: Session.() -> T, catch: (Throwable) -> Unit) : T?{
        val session = HibernateUtils.getSessionFactory().openSession()
        var result: T? = null
        try {
            session.transaction.begin()
            result = transaction(session)
            session.transaction.commit()
        } catch (ex: Exception) {
            catch(ex)
        } finally {
            session.close()
        }
        return result
    }
}

infix fun String.eq(value: Any) = Restrictions.eq(this, value)!!
infix fun Criterion.and(value: Criterion) = Restrictions.and(this, value)!!
infix fun Criterion.or(value: Criterion) = Restrictions.or(this, value)!!