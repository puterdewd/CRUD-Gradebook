package edu.asu.bsenetza.crud.gradebook.bsenetzasrv.jaxb.model;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author bsenetza
 */
@XmlRootElement
@XmlType(propOrder = {
    "gradedItemId",
    "description",
    "percentage",
    "grade"})
public class GradedItem {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private int gradedItemId;
    private String description;
    private float percentage;
    private List<Grade> grade = new ArrayList<>();

    //   private Grade grade;
    public GradedItem() {
        LOG.info("Creating a GradedItem object");
    }

    public int getGradedItemId() {
        return gradedItemId;
    }

    @XmlElement
    public void setGradedItemId(int gradedItemId) {
        LOG.info("Setting the gradedItemId to {}", gradedItemId);

        this.gradedItemId = gradedItemId;

        LOG.debug("The updated GradedItem = {}", this);
    }

    public String getDescription() {
        return description;
    }

    @XmlElement
    public void setDescription(String description) {
        LOG.info("Setting the description to {}", description);

        this.description = description;

        LOG.debug("The updated GradedItem = {}", this);
    }

    public float getPercentage() {
        return percentage;
    }

    @XmlElement
    public void setPercentage(float percentage) {
        LOG.info("Setting the percentage to {}", percentage);

        this.percentage = percentage;

        LOG.debug("The updated GradedItem = {}", this);
    }

    public List<Grade> getGrade() {
        return grade;
    }

    public void setGrade(List<Grade> grade) {
        this.grade = grade;
    }

    public void addGrade(Grade grade) {
        LOG.info("Adding the grade {}", grade);

        this.grade.add(grade);
    }

    public boolean containsStudentId(int studentId) {
        for (Grade grade : grade) {
            if (studentId == grade.getStudentId()) {
                return true;
            }
        }
        return false;
    }

    public Grade getGradeByStudentId(int studentId) {
        for (Grade grade : grade) {
            if (studentId == grade.getStudentId()) {
                return grade;
            }
        }
        return null;
    }

    public void removeGradeByStudentId(int studentId) {
        for (Grade grade : grade) {
            if (studentId == grade.getStudentId()) {
                this.grade.remove(grade);
                return;
            }
        }
    }

    @Override
    public String toString() {

        return "GradedItem{"
                + "gradedItemId=" + gradedItemId
                + ", description=" + description
                + ", percentage=" + percentage
                + ", grade=" + grade
                + '}';
    }
}
