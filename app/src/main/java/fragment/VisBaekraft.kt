package fragment

import adapter.FragmentRecycleView
import adapter.GridRecycleBViewer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.greenpayremastered.R
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import models.BaerkraftDataModel

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class VisBaekraft : Fragment() {

    private var knapp: Button? = null
    private var brecyleViewer: RecyclerView? = null
    private var param1: String? = null
    private var param2: String? = null
    private var BdataList: ArrayList<BaerkraftDataModel>? = null
    private var FFconToB: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_vis_baekraft, container, false)
        brecyleViewer = view.findViewById(R.id.BrecycleView)
        brecyleViewer?.layoutManager = GridLayoutManager(getView()?.context, 2)
        brecyleViewer?.setHasFixedSize(true)

        fetchDatabase()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //fetchDatabase()
        //brecyleViewer.layoutManager = GridLayoutManager(activity)
        brecyleViewer?.layoutManager = GridLayoutManager(getView()?.context, 2)
        brecyleViewer?.setHasFixedSize(true)
    }

    fun fetchDatabase(): GridRecycleBViewer? {
        //val adapter = FragmentRecycleView(datalist, this)
        val Badapter = GridRecycleBViewer(BdataList,this)
        FFconToB = FirebaseFirestore.getInstance()
        FFconToB!!.collection("Development-goals").get().addOnSuccessListener(OnSuccessListener<QuerySnapshot> { queryDocumentSnapshots ->
                val list = queryDocumentSnapshots.documents
                for (d in list) {
                    val item = d.toObject(BaerkraftDataModel::class.java)
                    BdataList?.add(item!!)
                }
                //recyclerviewFrag.setAdapter(adapter);
            })
        Badapter.notifyDataSetChanged()
        return Badapter
    }

    companion object
    {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            VisBaekraft().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
        fun newInstance() = VisBaekraft()
        }
}
