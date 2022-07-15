package online.jutter.smartcity.data.db.ent

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "admin_hakaton_users")
class UserEntity {
    @Id
    var id: Int = 0
    var firstname: String = ""
    var lastname: String = ""
    var phone: String = ""
    var city: String = ""
    var avatar: String = ""
    @Column(name = "team_id")
    var teamId: Int? = 0
}