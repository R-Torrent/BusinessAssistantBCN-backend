package com.businessassistantbcn.opendata.dto.bigmalls;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({ "register_id","prefix","suffix",/*"name",*/"created","modified","status","status_name","core_type","core_type_name",
        "body","tickets_data",/*"addresses",*/"entity_types_data","attribute_categories"/*,"values"*/,"from_relationships","to_relationships",
        /*"classifications_data",*/"secondary_filters_data","timetable","image_data","gallery_data","warnings","geo_epgs_25831",
        "geo_epgs_23031","geo_epgs_4326","is_section_of_data","sections_data","start_date","end_date","estimated_dates",
        "languages_data","type","type_name","period","period_name","event_status_name","event_status","ical"
})

/**
 * @JsonGetter for json deserialization
 * @JsonSetter for json serialization
 */
public class BigMallsDto {

    private String name;
    @JsonUnwrapped
    private List<ContactDto> values; // contact
    private List<ClassificationDataDto> classifications_data; // activities
    private List<AddressDto> addresses;

    @JsonGetter("activities") // deserialize clasification_data activities
    public List<ClassificationDataDto> getClassifications_data() {
        return classifications_data;
    }

    @JsonSetter("classifications_data")
    public void setClassifications_data(List<ClassificationDataDto> classifications_data) {
        this.classifications_data = classifications_data;
    }

    @JsonGetter("values")
    public ContactDto getValues() {

        ContactDto newContactDto = new ContactDto();;
        for(ContactDto c:values){

            if(c.getEmail_value() !=null){
                newContactDto.setEmail_value(c.getEmail_value());
            }

            if(c.getUrl_value() != null){
                newContactDto.setUrl_value(c.getUrl_value());
            }

            if(c.getPhone_value() != null){
                newContactDto.setPhone_value(c.getPhone_value());
            }
        }
        return newContactDto;
    }

    @JsonGetter("addresses")
    public List<AddressDto> getAddresses() {
        return addresses;
    }
}
