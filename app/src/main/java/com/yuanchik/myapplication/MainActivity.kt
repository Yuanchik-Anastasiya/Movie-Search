package com.yuanchik.myapplication

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var filmsAdapter: FilmListRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val topAppBar = findViewById<MaterialToolbar>(R.id.topAppBar)
        val bottom_navigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        topAppBar.setOnMenuItemClickListener() {
            when (it.itemId) {
                R.id.settings -> {
                    Toast.makeText(this, "Настройки", Toast.LENGTH_SHORT).show()
                    true
                }

                else -> false
            }
        }

        bottom_navigation.setOnItemSelectedListener() {

            when (it.itemId) {
                R.id.favorites -> {
                    Toast.makeText(this, "Избранное", Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.watch_later -> {
                    Toast.makeText(this, "Посмотреть похже", Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.selections -> {
                    Toast.makeText(this, "Подборки", Toast.LENGTH_SHORT).show()
                    true
                }

                else -> false
            }
        }
        val filmsDataBase = listOf(
            Film(
                "Веном:Последний танец",
                R.drawable.venom,
                "Будущий американский супергеройский фильм по мотивам комиксов Marvel Comics об одноимённом антигерое; продолжение картины «Веном 2», заключительная часть трилогии «Веном» и пятый кинокомикс в медиафраншизе «Вселенная Человека-паука от Sony». Производится компанией Columbia Pictures в сотрудничестве с Marvel и будет выпущен в прокат дистрибьютором Sony Pictures Releasing. Режиссёром и продюсером фильма выступила Келли Марсел, она же написала сценарий на основе сюжета, созданного ею совместно с Томом Харди - исполнителем роли Эдди Брока / Венома. В фильме также сыграли Чиветел Эджиофор, Джуно Темпл, Рис Иванс, Пегги Лу, Аланна Юбак и Стивен Грэм."
            ),
            Film(
                "Пчеловод",
                R.drawable.beekeeper,
                "Адам Клэй живет в тихом пригороде, занимается разведением пчёл и не распространяется о своей прошлой жизни. Он дружит с пожилой соседкой миссис Паркер, у которой арендует сарай. Однажды миссис Паркер становится жертвой кибермошенников, которые похищают деньги со всех её счетов. Не в силах вынести такой удар, женщина сводит счёты с жизнью. Осознав, что закон в данной ситуации бессилен, Адам решает применить все свои боевые навыки, чтобы вычислить преступников и отомстить."
            ),
            Film(
                "Гадкий я 4",
                R.drawable.despicable_me4,
                "Грю, Люси и их девочки — Марго, Эдит и Агнес — приветствуют нового члена семьи, Грю-младшего, который намерен мучить своего отца. Грю сталкивается с новым врагом в лице Максима Ле Маля и его роковой подруги Валентины, в связи с чем семья вынуждена бежать."
            ),
            Film(
                "Чужой: Ромул",
                R.drawable.alien,
                "Исследуя заброшенную космическую станцию, группа колонизаторов сталкивается с самой ужасающей формой жизни во Вселенной."
            ),
            Film(
                "Головоломка 2",
                R.drawable.puzzle2,
                "Мозг Райли внезапно подвергается капитальному ремонту в тот момент, когда необходимо освободить место для кое-чего совершенно неожиданного: новых эмоций. Радость, Грусть, Гнев, Страх и Отвращение никак не ожидали появления некой Тревожности. И похоже, не только её."
            ),
            Film(
                "Плохие парни до конца",
                R.drawable.the_bad_guys_to_the_end,
                "Полицейские Майами Маркус Бернетт и Майк Лоури узнают из теленовостей, что их погибшего капитана полиции Конрада Говарда обвиняют в коррупции и связях с наркокартелями. Маркус и Майк отказываются в это верить, а вскоре получают видеозапись от самого Говарда, снятую им перед смертью, в которой он просит найти своих убийц и восстановить его честное имя. Сделать это не так просто, теперь охота объявлена на самих Бернетта и Лоури. Парни вынуждены пуститься в бега."
            ),
            Film(
                "Планета обезьян: Новое царство",
                R.drawable.planet_of_the_apes,
                "Несколько поколений после правления Цезаря. Обезьяны являются доминирующим видом, живущим в гармонии, а люди вынуждены оставаться в тени. Пока новый тиранический лидер обезьян строит свою империю, один молодой шимпанзе отправляется в путешествие, которое заставит его усомниться во всём, что он знал о прошлом, и сделать выбор, который определит будущее как обезьян, так и людей."
            ),
        )
        val main_recycler = findViewById<View>(R.id.main_recycler)
        main_recycler.apply {
            filmsAdapter = FilmListRecyclerAdapter(object : FilmListRecyclerAdapter.OnItemClickListener{
                override fun click(film: Film) {
                    val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                    startActivity(intent)
                }
            })
            adapter = filmsAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            val decorator = TopSpacingItemDecoration(8)
            addItemDecoration(decorator)
        }
        filmsAdapter.addItems(filmsDataBase)
    }

    private fun addItemDecoration(decorator: TopSpacingItemDecoration) {

    }




}