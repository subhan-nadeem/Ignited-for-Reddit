package com.subhan_nadeem.ignite.subreddit_selection;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DividerItemDecoration;
import android.text.InputType;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ProgressBar;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.subhan_nadeem.ignite.R;
import com.subhan_nadeem.ignite.databinding.FragmentSubredditSelectionBinding;
import com.subhan_nadeem.ignite.models.Keyword;


public class SubredditSelectionFragment extends LifecycleFragment {

    private OnSubredditSavedListener mCallback;
    private FragmentSubredditSelectionBinding mBinding;

    public static SubredditSelectionFragment newInstance() {
        return new SubredditSelectionFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mCallback = (OnSubredditSavedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnSubredditSavedListener");
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_subreddit_selection, menu);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        mBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_subreddit_selection, container, false);

        SubredditSelectionViewModel viewModel
                = ViewModelProviders.of(this).get(SubredditSelectionViewModel.class);

        TagAdapter tagAdapter = new TagAdapter(viewModel.getSubredditAddition().getValue().getKeywords());

        mBinding.contentSubredditSelection.tagsRecyclerView.setAdapter(tagAdapter);
        mBinding.contentSubredditSelection.tagsRecyclerView
                .addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        mBinding.setViewModel(viewModel);

        mBinding.setView(this);

        return mBinding.getRoot();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_edit_item) {

            SubredditSelectionViewModel viewModel =
                    ViewModelProviders.of(this).get(SubredditSelectionViewModel.class);


            if (viewModel.isSubredditNameEmpty())
                showEmptySubredditSnackbar();
            else {
                viewModel.saveSubreddit();

                showPostSaveDialog(viewModel);
            }

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Shown after saving subreddit
     */
    private void showPostSaveDialog(final SubredditSelectionViewModel viewModel) {

        new MaterialDialog.Builder(getContext())
                .title(R.string.subreddit_saved)
                .content("Do you want to view this subreddit now?")
                .positiveText(R.string.yes)
                .negativeText(R.string.no)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        mCallback.onSubredditSaved(true,
                                viewModel.getSubredditAddition().getValue().getSubredditName());
                    }
                })
                .show();
    }

    private void showEmptySubredditSnackbar() {
        Snackbar.make(getView().findViewById(R.id.addFabMenu),
                R.string.no_entered_subreddit_name, Snackbar.LENGTH_LONG).show();
    }

    public void showNewTagDialog(final String typeOfTag) {
        mBinding.addFabMenu.close(true); // Close the menu manually

        SpannableStringBuilder contentString = createDialogContentText(typeOfTag);

        // Dialog builder starts here
        new MaterialDialog.Builder(getContext())
                .title(R.string.new_tag)
                .content(contentString)
                .positiveText(R.string.done)
                .negativeText(R.string.cancel)
                .inputType(InputType.TYPE_CLASS_TEXT)
                .input("[FRESH], Highlights, New...", null, new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                        // Nothing for now, no reaction to live input changes
                    }
                })
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        String enteredTag = dialog.getInputEditText().getText().toString();

                        Keyword newTag = new Keyword(enteredTag, typeOfTag);

                        saveKeyword(newTag);

                    }
                })
                .show();
    }

    private void saveKeyword(Keyword newKeyword) {
        SubredditSelectionViewModel viewModel =
                ViewModelProviders.of(this).get(SubredditSelectionViewModel.class);

        viewModel.getSubredditAddition().getValue().getKeywords().add(newKeyword);
    }

    @NonNull
    private SpannableStringBuilder createDialogContentText(String typeOfTag) {
        SpannableStringBuilder builder = new SpannableStringBuilder();

        SpannableString boldedTagString = new SpannableString(typeOfTag);
        boldedTagString.setSpan(new StyleSpan(Typeface.BOLD), 0,
                boldedTagString.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        SpannableString tagPrefix = new SpannableString("Enter a new ");
        SpannableString tagSuffix = new SpannableString(" tag you would like to be notified about");

        builder.append(tagPrefix).append(boldedTagString).append(tagSuffix);
        return builder;
    }

    private void initializeEditText() {
        mBinding.contentSubredditSelection.subredditEditText.setThreshold(2);
        mBinding.contentSubredditSelection.subredditEditText
                .setAdapter(new SubredditAutocompleteAdapter(getContext()));

        mBinding.contentSubredditSelection.subredditEditText.setLoadingIndicator(
                (ProgressBar) mBinding.getRoot().findViewById(R.id.subredditEditTextProgressBar));

        mBinding.contentSubredditSelection.subredditEditText
                .setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                        String subreddit = (String) adapterView.getItemAtPosition(position);
                        mBinding.contentSubredditSelection.subredditEditText.setText(subreddit);
                    }
                });
    }


}
