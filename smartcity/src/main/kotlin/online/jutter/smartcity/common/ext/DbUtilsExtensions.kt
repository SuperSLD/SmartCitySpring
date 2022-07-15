package online.jutter.smartcity.common.ext

import org.hibernate.Criteria
import org.hibernate.Session
import org.hibernate.criterion.Criterion
import org.hibernate.criterion.MatchMode
import org.hibernate.criterion.Restrictions
import java.io.Serializable

/**
 * Это все чтобы не плодить депрекейтед код
 * и быстро его исправить.
 *
 * Ну и упростить некоторые операции до одной строки.
 */

/**
 * Удаление всех элементов из базы
 * по сущности
 */
fun Session.removeAllInstances(clazz: Class<*>) {
    val hql = String.format("DELETE FROM %s", clazz.simpleName)
    val query = session.createQuery(hql)
    query.executeUpdate()
}

/**
 * Поиск сушности по базе через имя поля и
 * згачение поля.
 * @param name итя столбца.
 * @param value предпологаемое значение.
 */
@Suppress("UNCHECKED_CAST")
inline fun <reified T> Session.search(name: String, value: String): MutableList<T> {
    val query = session.createCriteria(T::class.java)
    query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
    query.add(Restrictions.like(name, value, MatchMode.ANYWHERE))
    return query.list() as MutableList<T>
}

/**
 * Список сущностей с наличием определенного
 * значения.
 * (SELECT * FROM table WHERE name = value;)
 * @param name итя столбца.
 * @param value значение.
 */
@Suppress("UNCHECKED_CAST")
inline fun <reified T> Session.getQuery(name: String, value: Serializable): MutableList<T> {
    val query = session.createCriteria(T::class.java)
    query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
    return query.add(Restrictions.eq(name, value)).list() as MutableList<T>
}

/**
 * Список сущностей с наличием определенного
 * значения.
 * (SELECT * FROM table WHERE name = value;)
 * @param restrictions выражения для филтирации.
 */
inline fun <reified T> Session.getQuery(vararg restrictions: Criterion): MutableList<T> {
    val query = session.createCriteria(T::class.java)
    query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
    for (restriction in restrictions) {
        query.add(restriction)
    }
    @Suppress("UNCHECKED_CAST")
    return query.list() as MutableList<T>
}

/**
 * Список всех сущностей.
 * (SELECT * FROM table;)
 */
@Suppress("UNCHECKED_CAST")
inline fun <reified T : Any> Session.getAll(): MutableList<T> {
    val query = session.createCriteria(T::class.java)
    query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
    return query.list() as MutableList<T>
}

/**
 * Список всех сущностей.
 * (SELECT * FROM table;)
 */
@Suppress("UNCHECKED_CAST")
fun <T> Session.getAll(clazz: Class<*>): MutableList<T> {
    val query = session.createCriteria(clazz)
    query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
    return query.list() as MutableList<T>
}

