package com.heavenstar.zandi.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHIssueComment;
import org.kohsuke.github.GHIssueState;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.kohsuke.github.PagedIterable;



public class GItServiceImpl {
	
	public static void main(String[] args) throws IOException {
        // jwt token을 사용하여 auth
        String my_jwt_token = "";
        GitHub gitHub = new GitHubBuilder().withOAuthToken(my_jwt_token).build();

        // repository 할당
        GHRepository repository = gitHub.getRepository("GOGOYS/Zandi");

        // issue 리스트
        List<GHIssue> issues = repository.getIssues(GHIssueState.ALL);
        Map<String, int[]> participantHash = new HashMap<String, int[]>();

        for (int i = 0; i < issues.size(); i++) {
            PagedIterable<GHIssueComment> ghIssueComments = issues.get(i).listComments();
            for (GHIssueComment ghIssueComment : ghIssueComments) {
                String userName = ghIssueComment.getUser().getName();

                if(participantHash.containsKey(userName)) {
                    int[] ints = participantHash.get(userName);
                    ints[i] = 1;
                }
                else {
                    int[] ints = new int[issues.size()];
                    ints[i] = 1;
                    participantHash.put(userName, ints);
                }
            }
        }

        participantHash.forEach(
                (key, value) -> {
                    System.out.print("| " + key + " | ");
                    int value_sum = 0;
                    for (int i : value) {       // 1: 참여, 0: 미참여
                        System.out.print("." + i);
                        value_sum += i;
                    }
                    float percent = (value_sum * 100)/issues.size();
                    System.out.println(" | " + String.format("%.2f", percent) + "% |");

                }

        );
    }

}
