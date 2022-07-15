package online.jutter.smartcity.data.db.ent

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "admin_hakaton_places")
class PlaceEntity {
    
    @Id
    var id: Int = 0
    var name: String = ""
    var address: String = ""
    var x: Float = 0F
    var y: Float = 0F
    @Column(name = "struct_name")
    var structName: String = ""
    var floor: Int = 0
}