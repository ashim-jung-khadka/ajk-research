package io.ashimjk.swaggerui.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Objects;

/**
 * Resource
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-02-09T15:40:50.365+05:45[Asia/Kathmandu]")

public class Resource implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("reference")
    private String reference;

    @JsonProperty("fullName")
    private String fullName;

    @JsonProperty("idType")
    private String idType;

    @JsonProperty("startingDate")
    private String startingDate;

    @JsonProperty("reviewDate")
    private String reviewDate;

    @JsonProperty("endingDate")
    private String endingDate;

    public Resource reference(String reference) {
        this.reference = reference;
        return this;
    }

    /**
     * Get reference
     *
     * @return reference
     */
    @ApiModelProperty(value = "")
    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Resource fullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    /**
     * Full Name of the beneficiary
     *
     * @return fullName
     */
    @ApiModelProperty(value = "Full Name of the beneficiary")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Resource idType(String idType) {
        this.idType = idType;
        return this;
    }

    /**
     * Primary id type
     *
     * @return idType
     */
    @ApiModelProperty(value = "Primary id type")
    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    /**
     * First transaction date of the customer
     *
     * @return startingDate
     */
    @ApiModelProperty(value = "First transaction date of the customer")
    public String getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(String startingDate) {
        this.startingDate = startingDate;
    }

    public Resource reviewDate(String reviewDate) {
        this.reviewDate = reviewDate;
        return this;
    }

    /**
     * Date when the beneficiary was reviewed, usually between starting and ending date
     *
     * @return reviewDate
     */
    @ApiModelProperty(value = "Date when the beneficiary was reviewed, usually between starting and ending date")
    public String getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(String reviewDate) {
        this.reviewDate = reviewDate;
    }

    public Resource endingDate(String endingDate) {
        this.endingDate = endingDate;
        return this;
    }

    /**
     * Date when the contract is ended
     *
     * @return endingDate
     */
    @ApiModelProperty(value = "Date when the contract is ended")
    public String getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(String endingDate) {
        this.endingDate = endingDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Resource resource = (Resource) o;
        return Objects.equals(this.reference, resource.reference) &&
                Objects.equals(this.fullName, resource.fullName) &&
                Objects.equals(this.idType, resource.idType) &&
                Objects.equals(this.startingDate, resource.startingDate) &&
                Objects.equals(this.reviewDate, resource.reviewDate) &&
                Objects.equals(this.endingDate, resource.endingDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reference, fullName, idType, startingDate, reviewDate, endingDate);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Resource {\n");

        sb.append("    reference: ").append(toIndentedString(reference)).append("\n");
        sb.append("    fullName: ").append(toIndentedString(fullName)).append("\n");
        sb.append("    idType: ").append(toIndentedString(idType)).append("\n");
        sb.append("    startingDate: ").append(toIndentedString(startingDate)).append("\n");
        sb.append("    reviewDate: ").append(toIndentedString(reviewDate)).append("\n");
        sb.append("    endingDate: ").append(toIndentedString(endingDate)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

}

