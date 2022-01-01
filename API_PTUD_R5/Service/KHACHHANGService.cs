// <snippet_BookServiceClass>
using API_PTUD_R5.Model;
using MongoDB.Driver;
using System.Collections.Generic;
using System.Linq;

namespace API_PTUD_R5.Service
{
    public class KHACHHANGService
    {
        private readonly IMongoCollection<KHACHHANG> _books;

        // <snippet_BookServiceConstructor>
        public KHACHHANGService(IDatabaseSettings settings)
        {
            var client = new MongoClient(settings.ConnectionString);
            var database = client.GetDatabase(settings.DatabaseName);

            _books = database.GetCollection<KHACHHANG>(settings.KhachHangCollectionName);
        }
        // </snippet_BookServiceConstructor>

        public List<KHACHHANG> Get() =>
            _books.Find(book => true).ToList();

        public KHACHHANG Get(string id) =>
            _books.Find<KHACHHANG>(book => book.Id == id).FirstOrDefault();

        public KHACHHANG Create(KHACHHANG book)
        {
            _books.InsertOne(book);
            return book;
        }

        public void Update(string id, KHACHHANG bookIn) =>
            _books.ReplaceOne(book => book.Id == id, bookIn);

        public void Remove(KHACHHANG bookIn) =>
            _books.DeleteOne(book => book.Id == bookIn.Id);

        public void Remove(string id) =>
            _books.DeleteOne(book => book.Id == id);
    }
}
