package online.jutter.smartcity.data.db.ent

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "admin_hakaton_streams")
class StreamEntity {

    @Id
    var id: Int = 0
    var name: String = ""
    var description: String = ""
    var link: String = ""
    var preview: String = ""
}