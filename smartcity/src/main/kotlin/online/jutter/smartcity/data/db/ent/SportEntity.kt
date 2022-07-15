package online.jutter.smartcity.data.db.ent

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "admin_hakaton_sports")
class SportEntity {

    @Id
    var id: Int = 0
    var name: String = ""
    var description: String = ""
    @Column(name = "is_team")
    var isTeam: Boolean = false
}