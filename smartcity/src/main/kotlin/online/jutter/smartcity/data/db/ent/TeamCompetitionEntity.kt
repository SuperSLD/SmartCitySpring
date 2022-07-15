package online.jutter.smartcity.data.db.ent

import org.hibernate.annotations.Cascade
import org.hibernate.annotations.CascadeType
import javax.persistence.*

@Entity
@Table(name = "admin_hakaton_teams_competitions")
class TeamCompetitionEntity {

    @Id
    var id: Int = 0

    @Column(name = "team_id")
    var teamId: Int = 0

    @OneToOne(fetch = FetchType.EAGER)
    @Cascade(CascadeType.PERSIST)
    @JoinColumn(name = "competition_id")
    var competition: CompetitionEntity = CompetitionEntity()

    @Column(name = "rank_team")
    var rank: Int? = 0
}