using Csharp_Blog.Models;
using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Mvc;

namespace Csharp_Blog.Controllers
{
    public class ArticleController : Controller
    {

        // GET: Article
        public ActionResult Index()
        {
            return RedirectToAction("List");
        }

        public ActionResult List()
        {
            using (var datebase = new BlogDbContext())
            {
                var articles = datebase.Articles.Include(a => a.Author).ToList();
                return View(articles);
            }
        }

        public ActionResult Details(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }

            using (var database = new BlogDbContext())
            {
                var article = database.Articles.Where(a => a.Id == id).Include(a => a.Author).First();
                if (article == null)
                {
                    return HttpNotFound();
                }
                return View(article);
            }
            
        }
    }
}