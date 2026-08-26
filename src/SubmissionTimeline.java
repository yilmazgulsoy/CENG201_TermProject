import java.util.List;

public class SubmissionTimeline {
    private PlainSubmissionBST tree;

    public SubmissionTimeline() {
        this.tree = new PlainSubmissionBST();
    }

    public void addSubmission(Submission s) {
        if (s != null) {
            tree.insert(s);
        }
    }

    public List<Submission> getSubmissionsInWindow(long startTime, long endTime) {
        return tree.submittedBetween(startTime, endTime);
    }
}
//ağacı ödevlere entegre etme yeri