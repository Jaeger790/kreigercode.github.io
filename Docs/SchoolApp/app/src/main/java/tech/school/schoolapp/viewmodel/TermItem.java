package tech.school.schoolapp.viewmodel;

public class TermItem {
    private String termTitle;
    private String dateLabelStart;
    private String dateLabelEnd;
    private String termStart;
    private String termEnd;

    public TermItem(String termTitle, String dateLabelStart, String dateLabelEnd, String termStart, String termEnd) {
        this.termTitle = termTitle;
        this.dateLabelStart = dateLabelStart;
        this.dateLabelEnd = dateLabelEnd;
        this.termStart = termStart;
        this.termEnd = termEnd;
    }

    public TermItem(String termTitle, String termStart, String termEnd) {
        this.termTitle = termTitle;
        this.termStart = termStart;
        this.termEnd = termEnd;
    }

    public String getTermTitle() {
        return termTitle;
    }

    public void setTermTitle(String termTitle) {
        this.termTitle = termTitle;
    }

    public String getDateLabelStart() {
        return dateLabelStart;
    }

    public void setDateLabelStart(String dateLabelStart) {
        this.dateLabelStart = dateLabelStart;
    }

    public String getDateLabelEnd() {
        return dateLabelEnd;
    }

    public void setDateLabelEnd(String dateLabelEnd) {
        this.dateLabelEnd = dateLabelEnd;
    }

    public String getTermStart() {
        return termStart;
    }

    public void setTermStart(String termStart) {
        this.termStart = termStart;
    }

    public String getTermEnd() {
        return termEnd;
    }

    public void setTermEnd(String termEnd) {
        this.termEnd = termEnd;
    }
}
