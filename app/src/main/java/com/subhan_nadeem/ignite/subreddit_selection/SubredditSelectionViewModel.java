package com.subhan_nadeem.ignite.subreddit_selection;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;

import com.subhan_nadeem.ignite.models.SubredditAddition;
import com.subhan_nadeem.ignite.storage.SubredditRepository;

/**
 * Created by Subhan Nadeem on 2017-09-10.
 * ViewModel for SubredditSelectionFragment
 */

@SuppressWarnings("ConstantConditions")
public class SubredditSelectionViewModel extends AndroidViewModel {

    private MutableLiveData<SubredditAddition> mSubredditAddition = new MutableLiveData<>();

    public SubredditSelectionViewModel(Application application) {
        super(application);

        mSubredditAddition.setValue(new SubredditAddition());
    }

    public MutableLiveData<SubredditAddition> getSubredditAddition() {
        return mSubredditAddition;
    }

    void saveSubreddit() {
        SubredditRepository subredditRepository = new SubredditRepository();
        subredditRepository.addSubreddit(mSubredditAddition.getValue());
    }

    boolean isSubredditNameEmpty() {
        return mSubredditAddition.getValue().getSubredditName() == null ||
                mSubredditAddition.getValue().getSubredditName().isEmpty();
    }
}
