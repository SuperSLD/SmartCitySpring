package online.jutter.smartcity.data.db.ent

import online.jutter.smartcity.common.ext.toDateString
import online.jutter.smartcity.common.ext.toStringMonth
import org.hibernate.annotations.Cascade
import org.hibernate.annotations.CascadeType
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "admin_hakaton_competitions")
class CompetitionEntity {
    
    @Id
    var id: Int = 0

    var datetime: Date = Date()

    @OneToOne(fetch = FetchType.EAGER)
    @Cascade(CascadeType.PERSIST)
    @JoinColumn(name = "place_id")
    var place: PlaceEntity = PlaceEntity()

    @OneToOne(fetch = FetchType.EAGER)
    @Cascade(CascadeType.PERSIST)
    @JoinColumn(name = "sport_id")
    var sport: SportEntity = SportEntity()

    fun getDay(): String {
        val str  = Calendar.getInstance().apply { timeInMillis = datetime.time }.toDateString("dd.MM.yyyy").split(".")
        return "${str[0]} ${str[1].toInt().toStringMonth()} ${str[2]}"
    }

    fun getTime() = Calendar.getInstance().apply { timeInMillis = datetime.time }.toDateString("HH:mm")
}