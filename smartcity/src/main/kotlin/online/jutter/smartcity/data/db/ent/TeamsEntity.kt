package online.jutter.smartcity.data.db.ent

import org.hibernate.annotations.Cascade
import org.hibernate.annotations.CascadeType
import javax.persistence.*

@Entity
@Table(name = "admin_hakaton_teams")
class TeamsEntity {

    @Id
    var id: Int = 0
    var name: String = ""
    @Column(name = "is_searching")
    var isSearching: Boolean = false
    var password: String = ""

    @Column(name = "captain_id")
    var captainId: Int = 0

    @OneToMany(fetch = FetchType.EAGER)
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "team_id")
    var users: MutableList<UserEntity> = mutableListOf()
}