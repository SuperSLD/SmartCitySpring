package online.jutter.smartcity.data.db.ent

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "admin_hakaton_news")
class NewsEntity {
    @Id
    var id: Int = 0
    var title: String = ""
    var text: String = ""
    var image: String = ""
    @Column(name = "short_text")
    var shortText: String = ""
    @Column(name = "date_string")
    var dateString: String = "00.00.0000"
}