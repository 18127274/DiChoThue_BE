// <snippet_BookServiceClass>
using API_PTUD_R5.Model;
using MongoDB.Driver;
using System.Collections.Generic;
using System.Linq;

namespace API_PTUD_R5.Service
{
    public class SANPHAMService
    {
        private readonly IMongoCollection<SANPHAM> _books;

        // <snippet_BookServiceConstructor>
        public SANPHAMService(IDatabaseSettings settings)
        {
            var client = new MongoClient(settings.ConnectionString);
            var database = client.GetDatabase(settings.DatabaseName);

            _books = database.GetCollection<SANPHAM>(settings.SanPhamCollectionName);
        }
        // </snippet_BookServiceConstructor>

        public List<SANPHAM> Get() =>
            _books.Find(book => true).ToList();

        public SANPHAM Get(string id) =>
            _books.Find<SANPHAM>(book => book.Id == id).FirstOrDefault();

        public SANPHAM Create(SANPHAM book)
        {
            _books.InsertOne(book);
            return book;
        }

        public void Update(string id, SANPHAM bookIn) =>
            _books.ReplaceOne(book => book.Id == id, bookIn);

        public void Remove(SANPHAM bookIn) =>
            _books.DeleteOne(book => book.Id == bookIn.Id);

        public void Remove(string id) =>
            _books.DeleteOne(book => book.Id == id);
    }
}
// </snippet_BookServiceClass>

