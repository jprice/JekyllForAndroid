package gr.tsagi.jekyllforandroid;

import android.os.AsyncTask;
import android.util.Log;

import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.service.RepositoryService;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by tsagi on 1/29/14.
 */

public class JekyllRepo {

    public String getName(String user){
        String name;

        try{
            name = new CheckAllRepos().execute(user).get();
            return name;
        }catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return null;
    }

    private class CheckAllRepos extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {

        String user = params[0];

        RepositoryService repositoryService = new RepositoryService();

        List<Repository> repositories = null;
        try {
            repositories = repositoryService.getRepositories(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Repository repository1 : repositories) {
            Log.d("repos", repository1.getName());
            if(repository1.getName().contains(user + ".github."))
                return repository1.getName();
        }
        return null;
        }
    }
}
